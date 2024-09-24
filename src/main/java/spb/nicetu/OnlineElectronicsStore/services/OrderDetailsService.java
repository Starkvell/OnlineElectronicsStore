package spb.nicetu.OnlineElectronicsStore.services;

import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;
import spb.nicetu.OnlineElectronicsStore.models.Product;

public interface OrderDetailsService {
    OrderDetails createOrderDetails(int quantity, Order order, Product product);
}
