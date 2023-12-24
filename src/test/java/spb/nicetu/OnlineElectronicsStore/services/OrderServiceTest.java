package spb.nicetu.OnlineElectronicsStore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.OrdersRepository;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.OrderNotFoundException;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.UserNotFoundException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private ProductService productService;

    @Mock
    private OrderDetailsService orderDetailsService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testFindAll() {
        // Arrange
        List<Order> orders = Arrays.asList(
                new Order( new Date(), BigDecimal.valueOf(10000),"address", new User(),Arrays.asList(new OrderDetails())),
                new Order( new Date(), BigDecimal.valueOf(20000), "address", new User(),Arrays.asList(new OrderDetails()))
        );

        when(ordersRepository.findAll()).thenReturn(orders);

        // Act
        List<Order> result = orderService.findAll();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(orders);
    }

    @Test
    void testFindOne_Successful() {
        // Arrange
        Order order = new Order(1, new Date(), BigDecimal.TEN,"address",new User(), Arrays.asList(new OrderDetails()));
        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));

        // Act
        Order result = orderService.findOne(1);

        // Assert
        assertThat(result).isEqualTo(order);
    }

    @Test
    void testFindOne_OrderNotFound() {
        // Arrange
        int orderId = 999;

        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThatThrownBy(() -> orderService.findOne(orderId))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Could not find object order with ID:" + orderId);
    }

    @Test
    void testCreateOrder() {
        // Arrange
        Order order = new Order();
        OrderDetails orderDetails = new OrderDetails(10, BigDecimal.TEN, order, new Product(1, "Product", null, null, 10, BigDecimal.valueOf(100), null, null));
        order.setOrderDetails(Arrays.asList(orderDetails));

        when(orderDetailsService.createOrderDetails(anyInt(), any(Order.class), any(Product.class))).thenReturn(orderDetails);
        when(productService.getAvailableProductCount(anyInt())).thenReturn(10);

        // Act
        orderService.createOrder(order);

        // Assert
        verify(orderDetailsService, times(1)).createOrderDetails(anyInt(), any(Order.class), any(Product.class));
        verify(productService, times(1)).getAvailableProductCount(anyInt());
        verify(ordersRepository, times(1)).save(order);
    }


}