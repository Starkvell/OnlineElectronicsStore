package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.CartDTO;
import spb.nicetu.OnlineElectronicsStore.models.Cart;

@Mapper(uses = { CartItemMapper.class })
public interface CartMapper {

    CartMapper MAPPER = Mappers.getMapper(CartMapper.class);

    @Mappings({
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "totalCost", target = "totalCost"),
            @Mapping(source = "cartItems", target = "cartItems")
    })
    CartDTO toCartDTO(Cart cart);

    @InheritInverseConfiguration
    Cart toCart(CartDTO cartDTO);
}