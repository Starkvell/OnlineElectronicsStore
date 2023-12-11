package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.CartItem;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.CartsRepository;
import spb.nicetu.OnlineElectronicsStore.repositories.UserRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class CartsService {
    private final CartsRepository cartsRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public CartsService(CartsRepository cartsRepository, UserService userService, UserRepository userRepository) {
        this.cartsRepository = cartsRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public User createCart(int userId) {
        User user = userService.findOne(userId);
        Cart cart = new Cart(0, new BigDecimal(0));
        cart.setUser(user);
        user.setCart(cart);
        cartsRepository.save(cart);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void addToCart(Cart cart, CartItem cartItem) {
        cart.addItem(cartItem);
        cartsRepository.save(cart);
    }


}
