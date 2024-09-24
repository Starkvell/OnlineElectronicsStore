package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.CartItemDTO;
import spb.nicetu.OnlineElectronicsStore.dto.CartItemRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.CartItem;
import spb.nicetu.OnlineElectronicsStore.models.Product;

@Mapper
public interface CartItemMapper {
    CartItemMapper MAPPER = Mappers.getMapper(CartItemMapper.class);

    @Mappings({
            @Mapping(source = "product", target = "product"),
            @Mapping(source = "quantity", target = "quantity")
    })
    CartItemDTO toCartItemDTO(CartItem cartItem);


    @Mappings({
            @Mapping(source = "productId", target = "product"),
            @Mapping(source = "quantity", target = "quantity")
    })
    CartItem toCartItem(CartItemRequestDTO cartItemRequestDTO);

    @Mappings({
            @Mapping(source = "product", target = "product"),
            @Mapping(source = "quantity", target = "quantity")
    })
    CartItem toCartItem(CartItemDTO cartDTO);

    default Product map(int value){
        Product product = new Product();
        product.setId(value);
        return product;
    }
}
