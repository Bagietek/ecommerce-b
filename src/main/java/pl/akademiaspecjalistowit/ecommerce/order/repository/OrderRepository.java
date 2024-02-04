package pl.akademiaspecjalistowit.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.order.entity.ClientOrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<ClientOrderEntity, Long> {
}
