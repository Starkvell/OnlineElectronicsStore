package spb.nicetu.OnlineElectronicsStore.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.dto.CartDTO;
import spb.nicetu.OnlineElectronicsStore.dto.ProductDTO;
import spb.nicetu.OnlineElectronicsStore.models.Cart;
import spb.nicetu.OnlineElectronicsStore.models.Product;

@Component
public class CartMapper {
    private final ModelMapper modelMapper;

    public CartMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CartDTO convertToDTO(Cart cart) {
        return modelMapper.map(cart, CartDTO.class);
    }

    public Cart convertToEntity(CartDTO cartDTO) {
        return modelMapper.map(cartDTO, Cart.class);
    }
}
