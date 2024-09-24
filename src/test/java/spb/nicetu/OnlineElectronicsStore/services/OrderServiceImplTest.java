package spb.nicetu.OnlineElectronicsStore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.OrdersRepository;
import spb.nicetu.OnlineElectronicsStore.services.impl.OrderServiceImpl;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.OrderNotFoundException;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private ProductService productService;

    @Mock
    private OrderDetailsService orderDetailsService;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Test
    void testFindAll() {
        // Arrange
        List<Order> orders = Arrays.asList(
                new Order(new Date(), BigDecimal.valueOf(10000), "address", new User(), Arrays.asList(new OrderDetails())),
                new Order(new Date(), BigDecimal.valueOf(20000), "address", new User(), Arrays.asList(new OrderDetails()))
        );

        when(ordersRepository.findAll()).thenReturn(orders);

        // Act
        List<Order> result = orderServiceImpl.findAll();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(orders);
    }

    @Test
    void testFindOne_Successful() {
        // Arrange
        Order order = new Order(1, new Date(), BigDecimal.TEN, "address", new User(), Arrays.asList(new OrderDetails()));
        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));

        // Act
        Order result = orderServiceImpl.findOne(1);

        // Assert
        assertThat(result).isEqualTo(order);
    }

    @Test
    void testFindOne_OrderNotFound() {
        // Arrange
        int orderId = 999;

        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThatThrownBy(() -> orderServiceImpl.findOne(orderId))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Could not find object order with ID:" + orderId);
    }


}