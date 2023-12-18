package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDetailsRequestDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;

import java.util.List;

@Mapper
public interface OrderMapperIn {

    OrderMapperIn MAPPER = Mappers.getMapper(OrderMapperIn.class);


    @Mappings({
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(target = "product", source = "product_id"),
    })
    OrderDetails orderDetailsDtoToOrderDetails(OrderDetailsRequestDTO orderDetailsDTO);

    @Mappings({
            @Mapping(target = "address",source = "address"),
            @Mapping(target = "orderDetails", source = "orderDetails")
    })
    Order orderRequestDtoToOrder(OrderRequestDTO orderRequestDTO);

    List<Order> orderRequestDtosToOrders(List<OrderRequestDTO> orderRequestDTOs);
    List<OrderDetails> orderDetailsDtosToOrderDetails(List<OrderDetailsRequestDTO> orderDetailsDTOs);

    OrderDTO convertToDTO(Order order);
    Order convertToEntity(OrderDTO orderDTO);

    default Product map(int value){
        Product product = new Product();
        product.setId(value);
        return product;
    }
}
