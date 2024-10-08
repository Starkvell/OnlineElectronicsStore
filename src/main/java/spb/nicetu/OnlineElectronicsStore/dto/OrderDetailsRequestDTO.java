package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spb.nicetu.OnlineElectronicsStore.annotations.ExistingProductId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRequestDTO implements Serializable {

    @Min(value = 1)
    private int quantity;

    @NotNull
    @ExistingProductId
    private int product_id;

}
