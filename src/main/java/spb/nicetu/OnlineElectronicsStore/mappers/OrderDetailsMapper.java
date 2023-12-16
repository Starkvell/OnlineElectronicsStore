package spb.nicetu.OnlineElectronicsStore.mappers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDetailsDTO;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;

@Component
public class OrderDetailsMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public OrderDetailsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDetailsDTO convertToDTO(OrderDetailsDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetailsDTO.class);
    }

    public OrderDetails convertToEntity(OrderDetailsDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetails.class);
    }
}
