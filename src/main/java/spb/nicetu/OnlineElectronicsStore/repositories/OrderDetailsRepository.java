package spb.nicetu.OnlineElectronicsStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spb.nicetu.OnlineElectronicsStore.models.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
}
