package pl.akademiaspecjalistowit.ecommerce.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationEntity, Long> {

    Optional<VerificationEntity> findByToken(UUID token);

    void deleteByToken(UUID token);
}
