package pl.akademiaspecjalistowit.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderView;

import java.util.Set;
import java.util.UUID;

public interface OrderViewRepository extends JpaRepository<OrderView, Long> {

    Set<OrderView> getAllByTechnicalId(UUID technicalId);
}
