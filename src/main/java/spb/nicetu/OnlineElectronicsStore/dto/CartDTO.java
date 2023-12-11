package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link spb.nicetu.OnlineElectronicsStore.models.Cart} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO implements Serializable {
    private int quantity;
    private BigDecimal totalCost;
    private Set<CartItemDTO> cartItems;
}