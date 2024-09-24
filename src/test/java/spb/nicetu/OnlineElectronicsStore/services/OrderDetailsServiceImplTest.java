package spb.nicetu.OnlineElectronicsStore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.OrderDetailsRepository;
import spb.nicetu.OnlineElectronicsStore.services.impl.OrderDetailsServiceImpl;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderDetailsServiceImplTest {

    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderDetailsServiceImpl orderDetailsServiceImpl;

    @Test
    void testCreateOrderDetails() {
        // Arrange
        Order order = new Order();
        Product product = new Product(1, "Product", null, null, 10,
                BigDecimal.valueOf(100000), BigDecimal.valueOf(90000), null);
        OrderDetails expected = new OrderDetails(0, 2, new BigDecimal(180000), order, product);
        when(productService.findOne(product.getId())).thenReturn(product);
        when(orderDetailsRepository.save(any(OrderDetails.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        OrderDetails result = orderDetailsServiceImpl.createOrderDetails(2, order, product);

        // Assert
        verify(productService, times(1)).findOne(product.getId());
        verify(orderDetailsRepository, times(1)).save(any(OrderDetails.class));
        assertThat(result).isNotNull();
        assertThat(result.getQuantity()).isEqualTo(expected.getQuantity());
        assertThat(result.getOrder()).isEqualTo(expected.getOrder());
        assertThat(result.getPrice()).isEqualTo(expected.getPrice());
        assertThat(result.getProduct()).isEqualTo(expected.getProduct());
    }
}