package spb.nicetu.OnlineElectronicsStore.services;

import spb.nicetu.OnlineElectronicsStore.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findOne(Integer id);
}
