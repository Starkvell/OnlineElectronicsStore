package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.OrdersRepository;
import spb.nicetu.OnlineElectronicsStore.util.OrderNotFoundException;
import spb.nicetu.OnlineElectronicsStore.util.ProductNotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final ProductService productService;
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderService(OrdersRepository ordersRepository, ProductService productService, OrderDetailsService orderDetailsService) {
        this.ordersRepository = ordersRepository;
        this.productService = productService;
        this.orderDetailsService = orderDetailsService;
    }

    public List<Order> findAll(){
        return ordersRepository.findAll();
    }

    public Order findOne(Integer id){
        return ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional
    public void createOrder(Order order) {
        // Заполняем детали заказа данными и продуктами
        enrichOrder(order);

        ordersRepository.save(order);
    }

    private void enrichOrder(Order order) {
        List<OrderDetails> orderDetailsList = order.getOrderDetails().stream()
                .map(orderDetails -> orderDetailsService.createOrderDetails(
                        orderDetails.getQuantity(), order, orderDetails.getProduct()
                ))
                .collect(Collectors.toList());

        order.setOrderDetails(orderDetailsList);
        order.setCreatedAt(new Date());
        BigDecimal totalAmount = calculateTotalAmount(order);
        order.setTotalAmount(totalAmount);
    }

    /**
     * Рассчитывает общую сумму заказа, основываясь на деталях заказа.
     * @param order Заказ.
     * @return Общая сумма заказа.
     */
    private BigDecimal calculateTotalAmount(Order order) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(OrderDetails orderDetails: order.getOrderDetails()) {
            if (orderDetails.getProduct() == null) {
                throw new RuntimeException("Продукт в заказе не определен");
            }

            if (orderDetails.getQuantity() > productService.getAvailableProductCount(orderDetails.getProduct().getId())){
                throw new ProductNotFoundException("Количество товара в заказе превышает количество товара на складе");
            }


            totalAmount = totalAmount.add(orderDetails.getPrice());
        }
        return totalAmount;
    }


}
