package spb.nicetu.OnlineElectronicsStore.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Orders")
public class OrderController {

    private final OrderService orderServiceImpl;
    private final UserService userServiceImpl;


    @Autowired
    public OrderController(OrderService orderServiceImpl, UserService userServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()   // TODO: для админки
    @Hidden
    public List<OrderDTO> getOrders(){
        return orderServiceImpl.findAll().stream().map(OrderMapper.MAPPER::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")  // TODO: для админки
    @Hidden
    public OrderDTO getOrder(@PathVariable Integer id){
        return OrderMapper.MAPPER.convertToDTO(orderServiceImpl.findOne(id));
    }

    @Operation(
            summary = "Get all orders for the authenticated user",
            description = "Retrieves a list of all orders placed by the authenticated user."
    )
    @GetMapping("/current")
    public List<OrderDTO> getOrders(@AuthenticationPrincipal UserDetails userDetails){
        User user = userServiceImpl.findByEmail(userDetails.getUsername());
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
    @Operation(
            summary = "Create a new order for the authenticated user",
            description = "Creates a new order based on the provided request data."
    )
    @PostMapping("/current")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetails userDetails,
                                         @RequestBody @Valid OrderRequestDTO orderRequestDTO,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors()); //TODO: Handler
        }

        User user = userServiceImpl.findByEmail(userDetails.getUsername());
        orderServiceImpl.createOrder(orderRequestDTO,user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
