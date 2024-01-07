package pl.akademiaspecjalistowit.ecommerce.order.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.entity.ClientOrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<ClientOrderEntity, Long> {
}
