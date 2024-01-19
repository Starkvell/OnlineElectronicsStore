package spb.nicetu.OnlineElectronicsStore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock_quantity",nullable = false)
    private int stockQuantity;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToString.Exclude
    private List<Category> categories;


    public Product(String title, String description, String imageUrl, int stockQuantity, BigDecimal basePrice, BigDecimal discountPrice) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.basePrice = basePrice;
        this.discountPrice = discountPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && stockQuantity == product.stockQuantity && Objects.equals(title, product.title) && Objects.equals(description, product.description) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(basePrice, product.basePrice) && Objects.equals(discountPrice, product.discountPrice) && Objects.equals(categories, product.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, imageUrl, stockQuantity, basePrice, discountPrice, categories);
    }
}
