package spb.nicetu.OnlineElectronicsStore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.dto.CartItemRequestDTO;
import spb.nicetu.OnlineElectronicsStore.dto.CartRequestDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.CartMapper;
import spb.nicetu.OnlineElectronicsStore.models.*;
import spb.nicetu.OnlineElectronicsStore.repositories.CartsRepository;
import spb.nicetu.OnlineElectronicsStore.repositories.UserRepository;
import spb.nicetu.OnlineElectronicsStore.services.CartsService;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;
import spb.nicetu.OnlineElectronicsStore.services.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class CartsServiceImpl implements CartsService {
    private final CartsRepository cartsRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductService productService;

    @Autowired
    public CartsServiceImpl(CartsRepository cartsRepository, UserService userService, UserRepository userRepository, ProductService productService) {
        this.cartsRepository = cartsRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public User createCart(int userId) {
        User user = userService.findOne(userId);
        Cart cart = new Cart(0, new BigDecimal(0));
        cart.setUser(user);
        cartsRepository.save(cart);
        return user;
    }

    @Override
    @Transactional
    public void addToCart(Cart cart, CartItem cartItem) {
        cart.addItem(cartItem);
        cart.setQuantity(calculateQuantity(cart));
        cart.setTotalCost(calculateTotalCost(cart));
        cartsRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart createCart(CartRequestDTO cartRequestDTO, User user) {
        Cart cart = CartMapper.MAPPER.toCart(cartRequestDTO);

        enrichCart(cart, user);

        return cartsRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart updateCart(CartRequestDTO cartRequestDTO, User user) {
        Cart currentCart = user.getCart();
        Set<CartItemRequestDTO> updatedCartItems = cartRequestDTO.getCartItems();

        for (CartItemRequestDTO updatedCartItem : updatedCartItems) {
            int updatedQuantity = updatedCartItem.getQuantity(); // Получаем обновленное количство
            int updatedProductId = updatedCartItem.getProductId(); // Получаем обновленный productId

            CartItem currentCartItem = currentCart.getCartItemByProductId(updatedProductId);
            if (currentCartItem != null) {
                currentCartItem.setQuantity(updatedQuantity); // Если нашли такой продукт в корзине, обновляем количество
            } else {
                currentCart.addItem(new CartItem(productService.findOne(updatedProductId), updatedQuantity)); // Если не нашли, то создаем новый продукт
            }
        }
        currentCart.setQuantity(calculateQuantity(currentCart)); // Пересчитываем количество продуктов в корзине
        currentCart.setTotalCost(calculateTotalCost(currentCart)); // Пересчитываем общую стоимость

        return cartsRepository.save(currentCart);
    }

    @Override
    @Transactional
    public void deleteCart(Cart cart) {
        cartsRepository.delete(cart);
    }

    private void enrichCart(Cart cart, User user) {
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = productService.findOne(cartItem.getProduct().getId());
            cartItem.setProduct(product);
        }
        cart.setQuantity(calculateQuantity(cart));
        cart.setTotalCost(calculateTotalCost(cart));
        cart.setUser(user);
    }

    private BigDecimal calculateTotalCost(Cart cart) {
        BigDecimal totalCost = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct() == null) {
                throw new RuntimeException("Продукт в корзине не определен");
            }

            totalCost = totalCost.add(cartItem.getProduct().getDiscountPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }

        return totalCost;
    }

    private int calculateQuantity(Cart cart) {
        int totalQuantity = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            int quantity = cartItem.getQuantity();
            totalQuantity += quantity;
        }

        return totalQuantity;
    }
}

