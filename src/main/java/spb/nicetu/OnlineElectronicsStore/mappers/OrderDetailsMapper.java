package spb.nicetu.OnlineElectronicsStore.mappers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDetailsRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;

@Component
public class OrderDetailsMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public OrderDetailsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDetailsRequestDTO convertToDTO(OrderDetailsRequestDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetailsRequestDTO.class);
    }

    public OrderDetails convertToEntity(OrderDetailsRequestDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetails.class);
    }
}
