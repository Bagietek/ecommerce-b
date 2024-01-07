package pl.akademiaspecjalistowit.ecommerce.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.entity.ItemEntity;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query(value = "SELECT * FROM item WHERE category_id = :categoryId", nativeQuery = true)
    List<ItemEntity> findItemEntitiesByCategoryId(@Param("categoryId") Long categoryId);
}
