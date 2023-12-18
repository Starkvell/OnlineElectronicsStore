package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    /**
     * Получает список всех продуктов.
     * @return Список DTO продуктов.
     */
    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.findAll().stream().map(productMapper::convertToDTO).collect(Collectors.toList());
    }


    /**
     * Получает продукт по id.
     * @param id Id продукта.
     * @return DTO продукта.
     */
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Integer id){
        return productMapper.convertToDTO(productService.findOne(id));
    }


}
