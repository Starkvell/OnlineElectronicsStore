package spb.nicetu.OnlineElectronicsStore.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User owner;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order(Date createdAt, BigDecimal totalAmount, String address, User owner) {
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.address = address;
        this.owner = owner;
    }

    public void addOrderDetails(OrderDetails orderDetails){
        this.orderDetails.add(orderDetails);
    }

    public void removeOrderDetails(OrderDetails orderDetails){
        this.orderDetails.remove(orderDetails);
    }
}
