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

}
