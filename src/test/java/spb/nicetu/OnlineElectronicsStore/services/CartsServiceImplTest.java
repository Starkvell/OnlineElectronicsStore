package spb.nicetu.OnlineElectronicsStore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.nicetu.OnlineElectronicsStore.dto.CartItemRequestDTO;
import spb.nicetu.OnlineElectronicsStore.dto.CartRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.CartItem;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.CartsRepository;
import spb.nicetu.OnlineElectronicsStore.repositories.UserRepository;
import spb.nicetu.OnlineElectronicsStore.services.impl.CartsServiceImpl;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CartsServiceImplTest {
    @Mock
    private CartsRepository cartsRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductService productService;
    @InjectMocks
    private CartsServiceImpl cartsServiceImpl;


    @Test
    void testCreateCart() {
        // Arrange
        User user = new User();
        when(userService.findOne(1)).thenReturn(user);
        when(cartsRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = cartsServiceImpl.createCart(1);

        // Assert
        assertThat(result).isSameAs(user);
        verify(userService, times(1)).findOne(1);
        verify(cartsRepository, times(1)).save(argThat(savedCart ->
                savedCart.getUser().equals(result)
                        && savedCart.getQuantity() == 0
                        && savedCart.getTotalCost().equals(BigDecimal.ZERO)
                        && savedCart.getCartItems().isEmpty()));
    }

    @Test
    void testAddToCart() {
        // Arrange
        Cart cart = new Cart();
        Product product = new Product(
                "Product", null, null, 5, new BigDecimal(100000), new BigDecimal(90000)
        );
        CartItem cartItem = new CartItem(product, 1);
        when(cartsRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        cartsServiceImpl.addToCart(cart, cartItem);

        // Assert
        verify(cartsRepository, times(1)).save(argThat(savedCart ->
                savedCart.equals(cart)
                        && savedCart.getCartItems().size() == 1
                        && savedCart.getCartItems().contains(cartItem)
                        && savedCart.getQuantity() == cartItem.getQuantity()
                        && Objects.equals(savedCart.getTotalCost(), cartItem.getProduct().getDiscountPrice())
        ));
    }

    @Test
    void testCreateCartWithCartRequestDTO() {
        // Arrange
        Set<CartItemRequestDTO> set = new HashSet<>(Arrays.asList(new CartItemRequestDTO(1, 1)));
        CartRequestDTO cartRequestDTO = new CartRequestDTO(set);
        Product product = new Product(1, "Product", null, null, 10, new BigDecimal(20000), new BigDecimal(10000), null);

        User user = new User();

        when(cartsRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(productService.findOne(1)).thenReturn(product);

        // Act
        Cart result = cartsServiceImpl.createCart(cartRequestDTO, user);

        // Assert
        assertThat(result).isNotNull();
        verify(cartsRepository, times(1)).save(any(Cart.class));
        assertThat(result).matches(savedCart -> savedCart.getQuantity() == 1
                && savedCart.getUser().equals(user)
                && savedCart.getTotalCost().equals(BigDecimal.valueOf(10000))
        );
    }

    @Test
    void testUpdateCart() {
        // Arrange
        Set<CartItemRequestDTO> set = new HashSet<>(Arrays.asList(new CartItemRequestDTO(1, 1)));
        CartRequestDTO cartRequestDTO = new CartRequestDTO(set);
        User user = new User();
        Product newProduct = new Product(
                1, "New Product in REQUEST", null, null, 5, new BigDecimal(100000), new BigDecimal(90000), null
        );
        Product alreadyInCart = new Product(
                2, "Product already in cart", null, null, 10, new BigDecimal(100000), new BigDecimal(70000), null
        );
        Set<CartItem> cartItemsAlreadyInCart = new HashSet<>(Arrays.asList(new CartItem(alreadyInCart, 1)));
        Cart currentCart = new Cart(1, 1, new BigDecimal(70000), user, cartItemsAlreadyInCart);
        user.setCart(currentCart);

        Set<CartItem> expectedSet = new HashSet<>(Arrays.asList(new CartItem(newProduct, 1), new CartItem(alreadyInCart, 1)));


        when(productService.findOne(1)).thenReturn(newProduct);
        when(cartsRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Cart result = cartsServiceImpl.updateCart(cartRequestDTO, user);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCartItems()).isEqualTo(expectedSet);
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getQuantity()).isEqualTo(2);
        assertThat(result.getTotalCost()).isEqualTo(BigDecimal.valueOf(160_000));
        verify(cartsRepository, times(1)).save(any(Cart.class));
        verify(productService, times(1)).findOne(1);

    }

}