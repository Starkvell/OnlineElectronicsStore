package spb.nicetu.OnlineElectronicsStore.controllers;

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
public class CategoriesController {

    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Получает список всех категорий товаров.
     * @return Список категорий товаров.
     */
    @GetMapping()
    public List<CategoryDTO> getCategories(){
        return categoryService.findAll().stream().map(CategoryMapper.MAPPER::toCategoryDTO).collect(Collectors.toList());
    }

    /**
     * Получает категорию по ее ID.
     * @param id ID категории.
     * @return Категория товара.
     */
    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Integer id){
        Category one = categoryService.findOne(id);
        return CategoryMapper.MAPPER.toCategoryDTO(one);
    }


}
