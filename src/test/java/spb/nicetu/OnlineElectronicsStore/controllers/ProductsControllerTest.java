package spb.nicetu.OnlineElectronicsStore.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = Arrays.asList(
                new Product(1, "Laptop", null, null, 1, BigDecimal.valueOf(1000), BigDecimal.valueOf(900), null),
                new Product(2, "Smartphone", null, null, 1, BigDecimal.valueOf(500), BigDecimal.valueOf(400), null)
        );
    }

    @Test
    void getProducts() throws Exception {
        when(productService.findAll()).thenReturn(products);

        // Act & Assert
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Laptop"))
                .andExpect(jsonPath("$[0].basePrice").value(1000))
                .andExpect(jsonPath("$[0].discountPrice").value(900))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Smartphone"))
                .andExpect(jsonPath("$[1].basePrice").value(500))
                .andExpect(jsonPath("$[1].discountPrice").value(400));
    }

    @Test
    void getProduct() throws Exception {
        when(productService.findOne(anyInt())).thenReturn(products.get(0));

        // Act & Assert
        mockMvc.perform(get("/api/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Laptop"))
                .andExpect(jsonPath("$.basePrice").value(1000))
                .andExpect(jsonPath("$.discountPrice").value(900));
    }
}