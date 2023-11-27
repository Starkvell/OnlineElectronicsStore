package spb.nicetu.OnlineElectronicsStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spb.nicetu.OnlineElectronicsStore.models.Cart;

@Repository
public interface CartsRepository extends JpaRepository<Cart,Integer> {
}
