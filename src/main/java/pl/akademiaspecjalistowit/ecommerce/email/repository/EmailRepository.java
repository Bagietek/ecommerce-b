package pl.akademiaspecjalistowit.ecommerce.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;
@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, String> {
}
