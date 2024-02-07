package pl.akademiaspecjalistowit.ecommerce.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.cart.entity.CartEntity;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    void deleteByTechnicalId(UUID technicalId);

    void deleteAllByClientId(ClientEntity client);

    List<CartEntity> getAllByItems(ItemEntity item);

    List<CartEntity> getAllByClientId(ClientEntity clientEntity);
}
