package spb.nicetu.OnlineElectronicsStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spb.nicetu.OnlineElectronicsStore.models.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Integer> {
}
