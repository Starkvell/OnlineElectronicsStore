package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDTO implements Serializable {
    private int productId;
    private int quantity;
}
