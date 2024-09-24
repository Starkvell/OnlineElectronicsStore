package spb.nicetu.OnlineElectronicsStore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spb.nicetu.OnlineElectronicsStore.dto.CategoryDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.CategoryMapper;
import spb.nicetu.OnlineElectronicsStore.models.Category;
import spb.nicetu.OnlineElectronicsStore.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories")
public class CategoriesController {

    private final CategoryService categoryServiceImpl;

    @Autowired
    public CategoriesController(CategoryService categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    /**
     * Получает список всех категорий товаров.
     * @return Список категорий товаров.
     */
    @Operation(
            summary = "Get all categories",
            description = "Retrieves a list of all categories."
    )
    @GetMapping()
    public List<CategoryDTO> getCategories(){
        return categoryServiceImpl.findAll().stream().map(CategoryMapper.MAPPER::toCategoryDTO).collect(Collectors.toList());
    }

    /**
     * Получает категорию по ее ID.
     * @param id ID категории.
     * @return Категория товара.
     */
    @Operation(
            summary = "Get a category by ID",
            description = "Retrieves a specific category by its ID."
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Integer id){
        Category one = categoryServiceImpl.findOne(id);
        return CategoryMapper.MAPPER.toCategoryDTO(one);
    }


}
