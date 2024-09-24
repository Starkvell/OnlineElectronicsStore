package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO implements Serializable {

    @NotBlank
    private String address;

    @NotEmpty
    @Valid
    private List<OrderDetailsRequestDTO> orderDetails;

    public List<OrderDetailsRequestDTO> getOrderDetails() {
        return orderDetails;
    }
}
