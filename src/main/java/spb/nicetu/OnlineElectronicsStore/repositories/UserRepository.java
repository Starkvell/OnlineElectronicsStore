package spb.nicetu.OnlineElectronicsStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spb.nicetu.OnlineElectronicsStore.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
