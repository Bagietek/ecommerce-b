package pl.akademiaspecjalistowit.ecommerce.seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.seller.entity.SellerEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Long> {

    Optional<SellerEntity> findByTechnicalId(UUID technicalId);
}
