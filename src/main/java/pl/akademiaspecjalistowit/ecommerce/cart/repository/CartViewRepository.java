package pl.akademiaspecjalistowit.ecommerce.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartView;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartViewRepository extends JpaRepository<CartView, Long> {

    List<CartView> getAllByClientId(UUID clientTechnicalId);
}
