package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.email.exception.VerificationNotFound;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;
import pl.akademiaspecjalistowit.ecommerce.email.repository.VerificationRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VerificationDataService {
    private final VerificationRepository verificationRepository;

    public VerificationEntity findByToken(UUID token){
        return verificationRepository.findByToken(token).orElseThrow(VerificationNotFound::new);
    }

    public void save(VerificationEntity verificationEntity){
        verificationRepository.save(verificationEntity);
    }

    public void delete(VerificationEntity verificationEntity){
        verificationRepository.delete(verificationEntity);
    }
}

