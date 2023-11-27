package spb.nicetu.OnlineElectronicsStore.mappers;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.dto.ProductDTO;
import spb.nicetu.OnlineElectronicsStore.models.Product;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
