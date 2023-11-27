package spb.nicetu.OnlineElectronicsStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spb.nicetu.OnlineElectronicsStore.models.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category,Integer> {
}
