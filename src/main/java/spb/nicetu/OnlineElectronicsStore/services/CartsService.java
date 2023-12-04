package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.repositories.CartsRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class CartsService {
    private final CartsRepository cartsRepository;
    private final UserService userService;

    @Autowired
    public CartsService(CartsRepository cartsRepository, UserService userService) {
        this.cartsRepository = cartsRepository;
        this.userService = userService;
    }

    @Transactional
    public Cart createCart(int userId) {
        User user = userService.findOne(userId);
        Cart cart = new Cart(0, new BigDecimal(0));
        cart.setUser(user);
        user.setCart(cart);
        return cart;
    }
}
