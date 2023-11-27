package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spb.nicetu.OnlineElectronicsStore.dto.ProductDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.ProductMapper;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductsController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.findAll().stream().map(productMapper::convertToDTO).collect(Collectors.toList());
    }
}
