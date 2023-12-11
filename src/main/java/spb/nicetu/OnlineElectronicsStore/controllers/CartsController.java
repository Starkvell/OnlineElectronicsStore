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
import spb.nicetu.OnlineElectronicsStore.security.UserDetailsImpl;
import spb.nicetu.OnlineElectronicsStore.services.CartsService;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;
import spb.nicetu.OnlineElectronicsStore.services.UserService;


@RestController
@RequestMapping("/api/cart")
public class CartsController {
    private final CartsService cartService;
    private final ProductService productService;
    private final CartMapper cartMapper;
    private final UserService userService;

    @Autowired
    public CartsController(CartsService cartService, CartMapper cartMapper, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
        this.productService = productService;
        this.userService = userService;
    }

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


    @GetMapping("/current")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Cart cart = user.getCart();

        if (cart == null) {
            return new ResponseEntity<>("User has not cart", HttpStatus.BAD_REQUEST); //TODO: Error response
        }


        CartDTO cartDTO = cartMapper.convertToDTO(cart);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

}
