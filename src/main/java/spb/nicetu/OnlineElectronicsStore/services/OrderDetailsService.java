package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;
import spb.nicetu.OnlineElectronicsStore.repositories.OrderDetailsRepository;

import java.math.BigDecimal;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductService productService;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, ProductService productService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.productService = productService;
    }


    public OrderDetails createOrderDetails(int quantity, Order order, Product product) {
        product = productService.findOne(product.getId());
        OrderDetails orderDetails = new OrderDetails(quantity, null, order, product);
        orderDetails.setPrice(calculatePrice(orderDetails));

        return orderDetailsRepository.save(orderDetails);
    }


    /**
     * Высчитывает общую цену детали заказа.
     *
     * @param orderDetails Деталь заказа.
     * @return Общая цена.
     */
    private BigDecimal calculatePrice(OrderDetails orderDetails) {
        if (orderDetails.getProduct() != null && orderDetails.getProduct().getDiscountPrice() != null) {
            return orderDetails.getProduct().getDiscountPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity()));
        } else {
            throw new IllegalArgumentException("Product or discount price is null for OrderDetails");
        }
    }


}
