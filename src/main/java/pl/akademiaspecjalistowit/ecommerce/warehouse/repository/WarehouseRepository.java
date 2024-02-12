package pl.akademiaspecjalistowit.ecommerce.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.seller.entity.SellerEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.model.WarehouseBo;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {

    Optional<WarehouseEntity> findByTechnicalId(UUID technicalId);

    void deleteByItemId(ItemEntity item);

    Optional<WarehouseEntity> findByItemId(ItemEntity item);

    Optional<WarehouseEntity> findBySellerEntity(SellerEntity sellerEntity);
}
