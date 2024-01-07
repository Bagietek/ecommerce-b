package pl.akademiaspecjalistowit.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.entity.ClientOrderEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderSummaryRepository extends JpaRepository<ClientOrderEntity, UUID> {
    @Query(value = "SELECT * FROM order_summary_view", nativeQuery = true)
    List<OrderSummary> findSummaries();
}
