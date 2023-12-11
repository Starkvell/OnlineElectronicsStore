package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spb.nicetu.OnlineElectronicsStore.models.Category;
import spb.nicetu.OnlineElectronicsStore.models.Product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private int id;

    private String name;

    private List<ProductDTO> products;

    private List<CategoryDTO> childCategories;
}
