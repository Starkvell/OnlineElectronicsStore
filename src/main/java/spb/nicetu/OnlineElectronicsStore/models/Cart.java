package spb.nicetu.OnlineElectronicsStore.models;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "totalCost", nullable = false)
    private BigDecimal totalCost;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
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

    public Cart(int quantity, BigDecimal totalCost) {
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,quantity,totalCost);
    }

    public CartItem getCartItemByProductId(int productId) {
        for (CartItem cartItem: this.cartItems){
            if (cartItem.getProduct().getId() == productId) {
                return cartItem;
            }
        }
        return null;
    }
}
