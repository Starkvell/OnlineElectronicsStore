package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spb.nicetu.OnlineElectronicsStore.models.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO implements Serializable {
    private int id;

    private BigDecimal price;

    @Min(value = 1)
    private int quantity;

    private Product product;
}
