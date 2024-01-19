package spb.nicetu.OnlineElectronicsStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.models.Category;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.CategoriesRepository;
import spb.nicetu.OnlineElectronicsStore.repositories.ProductsRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


@Component
public class DataLoader implements ApplicationRunner {

    private ProductsRepository productsRepository;
    private CategoriesRepository categoriesRepository;

    @Autowired
    public DataLoader(ProductsRepository productsRepository, CategoriesRepository categoriesRepository) {
        this.productsRepository = productsRepository;
        this.categoriesRepository = categoriesRepository;
    }


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        productsRepository.deleteAll();
        categoriesRepository.deleteAll();

        Product product1 = new Product("Смартфон Apple iPhone 14", "Крутой телефон", null, 5, new BigDecimal(115999), new BigDecimal(100000));
        Product product2 = new Product("Смартфон Apple iPhone 13", "Крутой телефон 2", null, 4, new BigDecimal(90000), new BigDecimal(80000));
        Product product3 = new Product("Смартфон Samsung Galaxy S8", "Крутой телефон 3", null, 3, new BigDecimal(80000), new BigDecimal(70000));
        Product product4 = new Product("Наушники Apple AirPods Pro", "Крутые наушники", null, 2, new BigDecimal(70000), new BigDecimal(60000));
        Product product5 = new Product("Чехол для Huawei P50", "Крутой чехол", null, 1, new BigDecimal(1000), new BigDecimal(500));
        Product product6 = new Product("Умная колонка Яндекс Станция", "Крутая колонка", null, 0, new BigDecimal(9000), new BigDecimal(8000));
        Product product7 = new Product("Смартфон Huawei P50", "Крутая телефон 4", null, 10, new BigDecimal(30000), new BigDecimal(20000));

        Category category1 = new Category("Смартфоны");
        Category category2 = new Category("Apple");
        Category category3 = new Category("Samsung");
        Category category4 = new Category("Сопутствующие товары");
        Category category5 = new Category("Наушники");
        Category category6 = new Category("Чехлы");
        Category category7 = new Category("Аудиотехника");
        Category category8 = new Category("Портативные колонки");

        category1.setChildCategories(new HashSet<>(Arrays.asList(category2,category3,category4)));
        category4.setChildCategories(new HashSet<>(Arrays.asList(category5,category6)));
        category7.setChildCategories(new HashSet<>(Arrays.asList(category5,category8)));

        categoriesRepository.save(category1);
        categoriesRepository.save(category2);
        categoriesRepository.save(category3);
        categoriesRepository.save(category4);
        categoriesRepository.save(category5);
        categoriesRepository.save(category6);
        categoriesRepository.save(category7);
        categoriesRepository.save(category8);

        product1.setCategories(Collections.singletonList(category2));
        product2.setCategories(Collections.singletonList(category2));
        product3.setCategories(Collections.singletonList(category3));
        product4.setCategories(Collections.singletonList(category5));
        product5.setCategories(Collections.singletonList(category6));
        product6.setCategories(Collections.singletonList(category8));
        product7.setCategories(Collections.singletonList(category1));

        productsRepository.save(product1);
        productsRepository.save(product2);
        productsRepository.save(product3);
        productsRepository.save(product4);
        productsRepository.save(product5);
        productsRepository.save(product6);
        productsRepository.save(product7);


    }
}
