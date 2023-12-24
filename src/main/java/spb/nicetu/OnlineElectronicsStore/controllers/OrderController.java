package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderRequestDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.OrderMapper;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.services.OrderService;
import spb.nicetu.OnlineElectronicsStore.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;


    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()   // TODO: для админки
    public List<OrderDTO> getOrders(){
        return orderService.findAll().stream().map(OrderMapper.MAPPER::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")  // TODO: для админки
    public OrderDTO getOrder(@PathVariable Integer id){
        return OrderMapper.MAPPER.convertToDTO(orderService.findOne(id));
    }

    @GetMapping("/current")
    public List<OrderDTO> getOrders(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.findByEmail(userDetails.getUsername());
        List<Order> orderList = user.getOrderList();

        return orderList.stream().map(OrderMapper.MAPPER::convertToDTO).collect(Collectors.toList());
    }


    /**
     * Создает заказ
     * @param userDetails   Данные аутентифицированного пользователя.
     * @param orderRequestDTO   Тело запроса для создания заказа.
     * @param bindingResult Представляет результаты привязки в процессе валидации.
     * @return HTTP STATUS OK при успешном создании.
     */
    @PostMapping("/current")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetails userDetails,
                                         @RequestBody @Valid OrderRequestDTO orderRequestDTO,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors()); //TODO: Handler
        }

        User user = userService.findByEmail(userDetails.getUsername());
        Order order = OrderMapper.MAPPER.toOrder(orderRequestDTO);
        order.setOwner(user);
        orderService.createOrder(order); /* TODO: Сделать уменьшение товара, на складе при оформлении заказа,
                                             сделать уменьшение товара в заказе, если на складе его меньше. */

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
