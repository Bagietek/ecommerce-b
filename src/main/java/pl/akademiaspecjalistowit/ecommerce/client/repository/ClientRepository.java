package pl.akademiaspecjalistowit.ecommerce.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public void deleteById(Long id);

    public ClientEntity getClientEntityByEmail(EmailEntity email);
}
