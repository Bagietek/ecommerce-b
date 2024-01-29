package pl.akademiaspecjalistowit.ecommerce.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public void deleteById(Long id);

    public Optional<ClientEntity> getClientEntityByEmail(String email);
}
