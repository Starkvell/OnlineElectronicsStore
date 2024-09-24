package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link spb.nicetu.OnlineElectronicsStore.models.CartItem} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO implements Serializable {
    private ProductDTO product;
    private int quantity;
}