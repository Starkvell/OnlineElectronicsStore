package spb.nicetu.OnlineElectronicsStore.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spb.nicetu.OnlineElectronicsStore.dto.CartDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.CartMapper;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.CartItem;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.models.User;
import spb.nicetu.OnlineElectronicsStore.services.CartsService;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;
import spb.nicetu.OnlineElectronicsStore.services.UserService;


@RestController
@RequestMapping("/api/cart")
public class CartsController {
    private final CartsService cartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartsController(CartsService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    /**
     *  Добавляет продукт в корзину аутентифицированного пользователя
     * @param userDetails Информация о текущем аутентифицированном пользователе.
     * @param productId  Идентификатор продукта, который требуется добавить в корзину.
     * @param quantity Количество единиц продукта, которые следует добавить в корзину.
     * @return ResponseEntity с HTTP Status Code 201 CREATED при успешном добавлении продукта в корзину
     *  или ошибку с HTTP Status Code 400.
     */
    @PostMapping("/add-product")
    public ResponseEntity<?> addProductToCart(@AuthenticationPrincipal UserDetails userDetails,
                                              @RequestParam("productId") int productId,
                                              @RequestParam("quantity") int quantity) {
        Product product = productService.findOne(productId);
        CartItem cartItem = new CartItem(product, quantity);
        User user = userService.findByEmail(userDetails.getUsername());

        if (user.getCart() == null) {
            user = cartService.createCart(user.getId());
        }

        Cart cart = user.getCart();
        cartService.addToCart(cart,cartItem);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * Получает информацию о текущей корзине аутентифицированного пользователя.
     * @param userDetails Информация о текущем аутентифицированном пользователе.
     * @return ResponseEntity с HTTP Status Code 200 OK и DTO корзины при успешном запросе,
     *  *         или ответ с ошибкой NOT FOUND и сообщением, если у пользователя нет корзины.
     */
    @GetMapping("/current")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Cart cart = user.getCart();

        if (cart == null) {
            return new ResponseEntity<>("User has not cart", HttpStatus.NOT_FOUND); //TODO: Error response
        }


        CartDTO cartDTO = CartMapper.MAPPER.toCartDTO(cart);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

}
