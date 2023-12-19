package spb.nicetu.OnlineElectronicsStore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonManagedReference
    @ToString.Exclude
    private List<Product> products;

    @ManyToMany
    @JoinTable(
            name = "category_relations",
            joinColumns = @JoinColumn(name = "parent_category_id"),
            inverseJoinColumns = @JoinColumn(name = "child_category_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<Category> childCategories;

    @ManyToMany(mappedBy = "childCategories")
    @ToString.Exclude
    private Set<Category> parentCategories;


    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Set<Category> parentCategories, Set<Category> childCategories) {
        this.name = name;
        this.parentCategories = parentCategories;
        this.childCategories = childCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
