package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDetailsDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;

import java.util.List;

@Mapper
public interface OrderMapperIn {

    OrderMapperIn MAPPER = Mappers.getMapper(OrderMapperIn.class);

//    @Mapping(source = "address", target = "address")
//    @Mapping(source = "orderDetails", target = "orderDetails")
//    Order toOrder(OrderRequestDTO orderRequestDTO);
//
//    @InheritInverseConfiguration
//    OrderRequestDTO fromOrder(Order order);


    @Mappings({
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(target = "product", source = "product_id"),
    })
    OrderDetails orderDetailsDtoToOrderDetails(OrderDetailsDTO orderDetailsDTO);

    @Mappings({
            @Mapping(target = "address",source = "address"),
            @Mapping(target = "orderDetails", source = "orderDetails")
    })
    Order orderRequestDtoToOrder(OrderRequestDTO orderRequestDTO);

    List<Order> orderRequestDtosToOrders(List<OrderRequestDTO> orderRequestDTOs);
    List<OrderDetails> orderDetailsDtosToOrderDetails(List<OrderDetailsDTO> orderDetailsDTOs);

    default Product map(int value){
        Product product = new Product();
        product.setId(value);
        return product;
    }
}
