package pl.akademiaspecjalistowit.ecommerce.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    void deleteById(Long id);

    Optional<ClientEntity> getClientEntityByEmail(String email);

    Optional<ClientEntity> getClientEntityByTechnicalId(UUID technicalId);
}
