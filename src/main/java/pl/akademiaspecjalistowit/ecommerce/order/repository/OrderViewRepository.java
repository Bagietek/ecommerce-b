package pl.akademiaspecjalistowit.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiaspecjalistowit.ecommerce.order.model.OrderView;

public interface OrderViewRepository extends JpaRepository<OrderView, Long> {
}
