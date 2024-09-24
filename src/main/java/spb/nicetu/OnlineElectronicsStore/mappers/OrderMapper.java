package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Order;

@Mapper(uses = { OrderDetailsMapper.class })
public interface OrderMapper {

    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);


    @Mappings({
            @Mapping(target = "address",source = "address"),
            @Mapping(target = "orderDetails", source = "orderDetails")
    })
    Order toOrder(OrderRequestDTO orderRequestDTO);

    Order toOrder(OrderDTO orderDTO);

    OrderDTO convertToDTO(Order order);


}
