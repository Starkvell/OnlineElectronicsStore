package spb.nicetu.OnlineElectronicsStore.services;

import org.springframework.stereotype.Service;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;

import java.math.BigDecimal;

@Service
public class OrderDetailsService {

    /**
     * Высчитывает общую цену детали заказа.
     * @param orderDetails Деталь заказа.
     * @return Общая цена.
     */
    public BigDecimal calculatePrice(OrderDetails orderDetails) {
        if (orderDetails.getProduct() != null && orderDetails.getProduct().getDiscountPrice() != null) {
            return orderDetails.getProduct().getDiscountPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity()));
        } else {
            throw new IllegalArgumentException("Product or discount price is null for OrderDetails");
        }
    }


}
