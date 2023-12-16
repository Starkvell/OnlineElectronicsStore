package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.ProductsRepository;
import spb.nicetu.OnlineElectronicsStore.util.ProductNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public Product findOne(Integer id){
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Получить текущее количество товаров на складе по ID продукта.
     * @param productId ID продукта
     * @return  Текущее количество товаров
     */
    public int getAvailableProductCount(int productId){
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return product.getStockQuantity();
    }

}
