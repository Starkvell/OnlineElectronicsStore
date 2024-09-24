package spb.nicetu.OnlineElectronicsStore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spb.nicetu.OnlineElectronicsStore.dto.ProductDTO;
import spb.nicetu.OnlineElectronicsStore.mappers.ProductMapper;
import spb.nicetu.OnlineElectronicsStore.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products")
public class ProductsController {

    private final ProductService productServiceImpl;

    @Autowired
    public ProductsController(ProductService productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    /**
     * Получает список всех продуктов.
     * @return Список DTO продуктов.
     */
    @Operation(
            summary = "Get all products",
            description = "Retrieves a list of all products available."
    )
    @GetMapping
    public List<ProductDTO> getProducts() {
        return productServiceImpl.findAll().stream().map(ProductMapper.MAPPER::toProductDTO).collect(Collectors.toList());
    }


    /**
     * Получает продукт по id.
     * @param id Id продукта.
     * @return DTO продукта.
     */
    @Operation(
            summary = "Get a product by ID",
            description = "Retrieves a specific product by its ID."
    )
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Integer id){
        return ProductMapper.MAPPER.toProductDTO(productServiceImpl.findOne(id));
    }


}
