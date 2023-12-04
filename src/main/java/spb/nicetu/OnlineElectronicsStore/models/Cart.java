package spb.nicetu.OnlineElectronicsStore.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> cartItems = new ArrayList<>();

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
}
