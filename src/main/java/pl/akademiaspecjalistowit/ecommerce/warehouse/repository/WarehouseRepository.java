package pl.akademiaspecjalistowit.ecommerce.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {

    Optional<WarehouseEntity> findByTechnicalId(UUID technicalId);

    Optional<WarehouseEntity> existsByItemId(ItemEntity itemId);
}
