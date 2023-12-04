package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link spb.nicetu.OnlineElectronicsStore.models.CartItem} entity
 */
@Data
public class CartItemDto implements Serializable {
    private final ProductDTO product;
    private final int quantity;
}