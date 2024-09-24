package spb.nicetu.OnlineElectronicsStore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.ProductsRepository;
import spb.nicetu.OnlineElectronicsStore.services.impl.ProductServiceImpl;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;


    @Test
    void testFindAll() {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product("Product1", null, null, 10, new BigDecimal(1000), new BigDecimal(900)),
                new Product("Product2",null,null,15, new BigDecimal(900),new BigDecimal(500))
        );

        when(productsRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productServiceImpl.findAll();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(products);
    }

    @Test
    void testFindOne() {
        // Arrange
        Product product = new Product("Product1", null, null, 10, new BigDecimal(1000), new BigDecimal(900));

        when(productsRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        Product result = productServiceImpl.findOne(1);

        // Assert
        assertThat(result).isEqualTo(product);
    }

    @Test
    void testGetAvailableProductCount_Successful() {
        // Arrange
        Product product = new Product("Product1", null, null, 10, new BigDecimal(1000), new BigDecimal(900));

        when(productsRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        int result = productServiceImpl.getAvailableProductCount(1);

        // Assert
        assertThat(result).isEqualTo(product.getStockQuantity());
    }

    @Test
    void testGetAvailableProductCount_NonExistingProduct() {
        // Arrange
        when(productsRepository.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThatThrownBy(() -> productServiceImpl.getAvailableProductCount(1))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Could not find object product with ID:1");
    }
}