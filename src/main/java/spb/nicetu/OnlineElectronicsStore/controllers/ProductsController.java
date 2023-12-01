package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spb.nicetu.OnlineElectronicsStore.dto.ProductDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.ProductMapper;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;
import spb.nicetu.OnlineElectronicsStore.util.ProductNotFoundException;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Integer id){
        return productMapper.convertToDTO(productService.findOne(id));
    }


    @ExceptionHandler
    private ResponseEntity<?> handleException(ProductNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
