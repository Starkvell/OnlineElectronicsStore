package spb.nicetu.OnlineElectronicsStore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
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
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
