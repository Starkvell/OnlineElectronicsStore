package spb.nicetu.OnlineElectronicsStore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.ProductsRepository;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.ProductNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Product findOne(Integer id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Получить текущее количество товаров на складе по ID продукта.
     *
     * @param productId ID продукта
     * @return Текущее количество товаров
     */
    @Override
    public int getAvailableProductCount(int productId) {
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return product.getStockQuantity();
    }

    /**
     * Метод обновляет количество продукта по указанному идентификатору.
     *
     * @param newQuantity новое значение количества продукта
     * @param productId   идентификатор продукта, для которого нужно обновить количество
     * @return обновленный продукт
     * @throws ProductNotFoundException если продукт не найден по указанному идентификатору
     */
    @Override
    public Product updateProductQuantity(int newQuantity, int productId) {
        // Получаем объект продукта из репозитория по идентификатору или выбрасываем исключение, если продукт не найден
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        // Обновляем количество продукта
        product.setStockQuantity(newQuantity);
        // Сохраняем обновленный продукт в репозитории и возвращаем его
        return productsRepository.save(product);
    }

    @Override
    public void reduceProductQuantity(int productId, int reduce) {
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        int stockQuantity = product.getStockQuantity();
        int newStockQuantity = Math.max(stockQuantity - reduce, 0);
        product.setStockQuantity(newStockQuantity);
    }

    @Override
    public boolean isAvailableInStock(int productId, int quantity) {
        Optional<Product> productOptional = productsRepository.findById(productId);

        return productOptional.map(product -> product.getStockQuantity() >= quantity).orElse(false);
    }


    @Override
    public boolean existsById(Integer productId) {
        return productsRepository.existsById(productId);
    }
}
