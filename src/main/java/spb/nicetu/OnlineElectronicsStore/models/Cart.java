package spb.nicetu.OnlineElectronicsStore.models;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @Setter(AccessLevel.NONE)
    @Transient()
    private int quantity;

    @Transient
    private BigDecimal totalCost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems = new HashSet<>();

    public void addItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void removeItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
    }

    public int getQuantity() {
        return cartItems.size();
    }

    public BigDecimal getTotalCost() {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            BigDecimal itemPrice = user != null ? cartItem.getProduct().getDiscountPrice(): cartItem.getProduct().getBasePrice();
            total = total.add(itemPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }

        return total;
    }

    public Cart(int quantity, BigDecimal totalCost) {
        this.quantity = quantity;
        this.totalCost = totalCost;
    }
}
