package spb.nicetu.OnlineElectronicsStore.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.dto.CartDTO;
import spb.nicetu.OnlineElectronicsStore.dto.CartItemDTO;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.CartItem;

@Component
public class CartItemMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CartItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CartItemDTO convertToDTO(CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemDTO.class);
    }

    public CartItem convertToEntity(CartItemDTO cartItemDTO) {
        return modelMapper.map(cartItemDTO, CartItem.class);
    }
}
