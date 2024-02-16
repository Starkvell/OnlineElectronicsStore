package spb.nicetu.OnlineElectronicsStore.services;

import spb.nicetu.OnlineElectronicsStore.dto.CartRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.CartItem;
import spb.nicetu.OnlineElectronicsStore.models.User;

import javax.transaction.Transactional;

public interface CartsService {
    @Transactional
    User createCart(int userId);

    @Transactional
    void addToCart(Cart cart, CartItem cartItem);

    @Transactional
    Cart createCart(CartRequestDTO cartRequestDTO, User user);

    @Transactional
    Cart updateCart(CartRequestDTO cartRequestDTO, User user);

    @Transactional
    void deleteCart(Cart cart);
}
