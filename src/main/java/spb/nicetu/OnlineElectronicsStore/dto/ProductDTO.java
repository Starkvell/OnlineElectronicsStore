package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spb.nicetu.OnlineElectronicsStore.models.Category;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    private String title;

    private String description;

    private String imageUrl;

    @NotNull
    @Min(value = 0, message = "Количество товара на складе должно быть больше или равна 0")
    private int stockQuantity;

    @NotNull
    @Min(value = 0, message = "Цена должна быть больше или равна 0")
    private BigDecimal basePrice;

    @Min(value = 0)
    private BigDecimal discountPrice;

}


