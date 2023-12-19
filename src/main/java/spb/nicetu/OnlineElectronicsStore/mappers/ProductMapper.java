package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.ProductDTO;
import spb.nicetu.OnlineElectronicsStore.models.Product;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "stockQuantity", target = "stockQuantity"),
            @Mapping(source = "basePrice", target = "basePrice"),
            @Mapping(source = "discountPrice", target = "discountPrice")
    })
    ProductDTO toProductDTO(Product product);

    @InheritInverseConfiguration
    Product toProduct(ProductDTO productDTO);
}
