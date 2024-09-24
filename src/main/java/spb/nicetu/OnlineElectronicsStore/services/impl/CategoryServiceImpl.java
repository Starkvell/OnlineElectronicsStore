package spb.nicetu.OnlineElectronicsStore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Category;
import spb.nicetu.OnlineElectronicsStore.repositories.CategoriesRepository;
import spb.nicetu.OnlineElectronicsStore.services.CategoryService;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.CategoryNotFoundException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoryServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override public List<Category> findAll(){
        return categoriesRepository.findAll();
    }

    @Override public Category findOne(Integer id){
        return categoriesRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
