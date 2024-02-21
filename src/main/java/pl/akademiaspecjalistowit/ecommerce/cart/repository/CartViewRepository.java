package pl.akademiaspecjalistowit.ecommerce.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartView;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartViewRepository extends JpaRepository<CartView, Long> {

    @Query("SELECT new CartView(name, description, price, categoryName, amount, code) FROM CartView " +
            "WHERE clientId = :technicalId")
    List<CartView> findByClientId(@Param("technicalId") UUID clientTechnicalId);
}
