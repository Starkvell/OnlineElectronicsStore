package spb.nicetu.OnlineElectronicsStore.models;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "total_amount",nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "address",nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User owner;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @ToString.Exclude
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
