package pl.akademiaspecjalistowit.ecommerce.item.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.item.dto.ItemDto;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.model.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query(value =
            "SELECT i.name as name, i.description as description, i.price as price, c.name as categoryName, w.number_of_products as numberOfProducts " +
                    "FROM item i " +
                    "JOIN warehouse w ON i.id = w.item_id " +
                    "JOIN category c ON c.id = i.category_id",
            nativeQuery = true)
    List<Map<String, Object>> findAllItemsWithAmount(Pageable pageable);
}
