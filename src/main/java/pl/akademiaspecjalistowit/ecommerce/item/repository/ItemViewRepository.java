package pl.akademiaspecjalistowit.ecommerce.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;

import java.util.List;

@Repository
public interface ItemViewRepository extends JpaRepository<ItemView, Long> {
    List<ItemView> findByCategoryName(String category);
}
