package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.CartItemDTO;
import spb.nicetu.OnlineElectronicsStore.models.CartItem;

@Mapper
public interface CartItemMapper {
    CartItemMapper MAPPER = Mappers.getMapper(CartItemMapper.class);

    @Mappings({
            @Mapping(source = "product", target = "product"),
            @Mapping(source = "quantity", target = "quantity")
    })
    CartItemDTO toCartItemDTO(CartItem cartItem);

    @InheritInverseConfiguration
    CartItem toCartItem(CartItemDTO cartDTO);

}
