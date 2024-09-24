package spb.nicetu.OnlineElectronicsStore.services;

import spb.nicetu.OnlineElectronicsStore.dto.OrderRequestDTO;
import spb.nicetu.OnlineElectronicsStore.models.Order;
import spb.nicetu.OnlineElectronicsStore.models.User;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findOne(Integer id);

    // Создает заказ
    @Transactional
    void createOrder(OrderRequestDTO orderRequestDTO, User user);
}
