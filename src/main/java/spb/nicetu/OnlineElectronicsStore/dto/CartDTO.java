package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A DTO for the {@link spb.nicetu.OnlineElectronicsStore.models.Cart} entity
 */
@Data
public class CartDTO implements Serializable {
    private final int quantity;
    private final BigDecimal totalCost;
    private final List<ProductDTO> products;
}