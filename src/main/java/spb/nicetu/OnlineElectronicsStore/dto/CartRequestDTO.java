package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO implements Serializable {
    private Set<CartItemRequestDTO> cartItems;
}
