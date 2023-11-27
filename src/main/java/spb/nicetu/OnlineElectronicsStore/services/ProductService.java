package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.ProductsRepository;

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

}
