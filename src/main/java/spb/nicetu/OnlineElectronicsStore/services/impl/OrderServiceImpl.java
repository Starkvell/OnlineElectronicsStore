package spb.nicetu.OnlineElectronicsStore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDetailsRequestDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderRequestDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.OrderMapper;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.OrdersRepository;
import spb.nicetu.OnlineElectronicsStore.services.OrderDetailsService;
import spb.nicetu.OnlineElectronicsStore.services.OrderService;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.OrderNotFoundException;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.ProductNotAvailableException;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.ProductNotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final ProductService productService;
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderServiceImpl(OrdersRepository ordersRepository, ProductService productService, OrderDetailsService orderDetailsService) {
        this.ordersRepository = ordersRepository;
        this.productService = productService;
        this.orderDetailsService = orderDetailsService;
    }

    @Override
    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Order findOne(Integer id) {
        return ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    // Создает заказ
    @Override
    @Transactional
    public void createOrder(OrderRequestDTO orderRequestDTO, User user) {
        Order order = OrderMapper.MAPPER.toOrder(orderRequestDTO);
        order.setOwner(user);

        // Уменьшение количества продуктов в заказе
        reduceProductsQuantity(orderRequestDTO);
        // Заполняем детали заказа данными и продуктами
        enrichOrder(order);

        ordersRepository.save(order);
    }

    // Уменьшение количества продуктов в заказе
    private void reduceProductsQuantity(OrderRequestDTO orderRequestDTO) {
        List<OrderDetailsRequestDTO> list = orderRequestDTO.getOrderDetails();
        for (OrderDetailsRequestDTO orderDetails : list) {
            int product_id = orderDetails.getProduct_id();
            int quantity = orderDetails.getQuantity();

            if (productService.isAvailableInStock(product_id, quantity)) {
                // Продукт доступен на складе, можно обрабатывать заказ
                productService.reduceProductQuantity(product_id, quantity); // Уменьшаем количество продукта на складе
            } else {
                // Продукт недоступен на складе
                throw new ProductNotAvailableException("Product is not available in stock"); //TODO: Сдлать обработчик
            }
        }
    }

    private void enrichOrder(Order order) {
        // Извлекаем список OrderDetails из заказа и каждый элмент OrderDetails сохраняем в БД
        // с помощью orderDetailsService.createOrderDetails
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
     *
     * @param order Заказ.
     * @return Общая сумма заказа.
     */
    private BigDecimal calculateTotalAmount(Order order) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderDetails orderDetails : order.getOrderDetails()) {
            if (orderDetails.getProduct() == null) {
                throw new RuntimeException("Продукт в заказе не определен");
            }

            if (orderDetails.getQuantity() > productService.getAvailableProductCount(orderDetails.getProduct().getId())) {
                throw new ProductNotFoundException("Количество товара в заказе превышает количество товара на складе");
            }


            totalAmount = totalAmount.add(orderDetails.getPrice());
        }
        return totalAmount;
    }


}
