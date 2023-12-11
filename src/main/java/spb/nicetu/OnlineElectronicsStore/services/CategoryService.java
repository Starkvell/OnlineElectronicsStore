package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.dto.CategoryDTO;
import spb.nicetu.OnlineElectronicsStore.models.Category;
import spb.nicetu.OnlineElectronicsStore.repositories.CategoriesRepository;
import spb.nicetu.OnlineElectronicsStore.util.CategoryNotFoundException;
import spb.nicetu.OnlineElectronicsStore.util.ProductNotFoundException;

import java.util.List;

@Service
public class CategoryService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> findAll(){
        return categoriesRepository.findAll();
    }

    public Category findOne(Integer id){
        return categoriesRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
