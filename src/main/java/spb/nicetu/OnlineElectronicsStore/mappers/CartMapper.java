package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.CartDTO;
import spb.nicetu.OnlineElectronicsStore.dto.CartRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Cart;

@Mapper(uses = { CartItemMapper.class })
public interface CartMapper {

    CartMapper MAPPER = Mappers.getMapper(CartMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "totalCost", target = "totalCost"),
            @Mapping(source = "cartItems", target = "cartItems")
    })
    CartDTO toCartDTO(Cart cart);


    @Mappings({
            @Mapping(source = "cartItems",target = "cartItems")
    })
    Cart toCart(CartRequestDTO cartRequestDTO);


    @InheritInverseConfiguration
    Cart toCart(CartDTO cartDTO);
}