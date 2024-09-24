package spb.nicetu.OnlineElectronicsStore.services;

import spb.nicetu.OnlineElectronicsStore.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findOne(Integer id);

    int getAvailableProductCount(int productId);

    Product updateProductQuantity(int newQuantity, int productId);

    void reduceProductQuantity(int productId, int reduce);

    boolean isAvailableInStock(int productId, int quantity);

    boolean existsById(Integer productId);
}
