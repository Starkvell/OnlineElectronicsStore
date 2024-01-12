package spb.nicetu.OnlineElectronicsStore.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import spb.nicetu.OnlineElectronicsStore.dto.CartRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.services.CartsService;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;
import spb.nicetu.OnlineElectronicsStore.services.UserService;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
class CartsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartsService cartsService;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;


    @Test
    @WithMockUser
    void testAddProductToCart() throws Exception {
        // Arrange
        User testUser = new User("Name","LastName", "email", "password");
        Cart testCart = new Cart(0,BigDecimal.ZERO);
        testUser.setCart(testCart);

        when(productService.findOne(anyInt())).thenReturn(new Product());
        when(userService.findByEmail(any())).thenReturn(testUser);

        doNothing().when(cartsService).addToCart(any(), any());


        // Act & Assert
        mockMvc.perform(post("/api/cart/add-product")
                        .param("productId", "1")
                        .param("quantity", "2"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void testGetCart() throws Exception {
        // Arrange
        User testUser = new User("Name","LastName", "email", "password");
        Cart testCart = new Cart(0,BigDecimal.ZERO);
        testUser.setCart(testCart);
        when(userService.findByEmail(any())).thenReturn(testUser); // Мокирование сервиса


        // Act & Assert
        mockMvc.perform(get("/api/cart/current"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testCreateCart() throws Exception{
        // Arrange
        when(userService.findByEmail(any())).thenReturn(new User()); // Мокирование сервиса
        when(cartsService.createCart(any(CartRequestDTO.class), any(User.class))).thenReturn(new Cart()); // Мокирование сервиса

        // Act & Assert
        mockMvc.perform(post("/api/cart/current")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new CartRequestDTO())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void deleteCart() throws Exception {
        // Arrange
        User testUser = new User("Name","LastName", "email", "password");
        Cart testCart = new Cart(0,BigDecimal.ZERO);
        testUser.setCart(testCart);
        when(userService.findByEmail(any())).thenReturn(testUser); // Мокирование сервиса


        // Act & Assert
        mockMvc.perform(delete("/api/cart/current"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void updateCart() throws Exception {
        // Arrange
        User testUser = new User("Name","LastName", "email", "password");
        Cart testCart = new Cart(0,BigDecimal.ZERO);
        testUser.setCart(testCart);

        when(userService.findByEmail(any())).thenReturn(testUser); // Мокирование сервиса
        when(cartsService.updateCart(any(CartRequestDTO.class), any(User.class))).thenReturn(new Cart()); // Мокирование сервиса

        // Act & Assert
        mockMvc.perform(patch("/api/cart/current")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new CartRequestDTO())))
                .andExpect(status().isOk());
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}