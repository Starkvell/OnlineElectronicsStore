package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDetailsDTO;
import spb.nicetu.OnlineElectronicsStore.dto.OrderDetailsRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;

@Mapper
public interface OrderDetailsMapper {
    OrderDetailsMapper MAPPER = Mappers.getMapper(OrderDetailsMapper.class);

    OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails);

    OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO);

    OrderDetailsRequestDTO toOrderDetailsRequestDTO(OrderDetails orderDetails);


    @Mappings({
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(target = "product", source = "product_id"),
    })
    OrderDetails toOrderDetails(OrderDetailsRequestDTO orderDetailsRequestDTO);


    default Product map(int value){
        Product product = new Product();
        product.setId(value);
        return product;
    }
}
