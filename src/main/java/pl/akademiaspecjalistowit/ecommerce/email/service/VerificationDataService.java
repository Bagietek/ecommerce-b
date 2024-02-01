package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.email.exception.VerificationNotFound;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;
import pl.akademiaspecjalistowit.ecommerce.email.mapper.VerificationMapper;
import pl.akademiaspecjalistowit.ecommerce.email.model.VerificationDto;
import pl.akademiaspecjalistowit.ecommerce.email.repository.VerificationRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VerificationDataService {
    private final VerificationRepository verificationRepository;
    private final VerificationMapper verificationMapper;

    public VerificationDto findByToken(UUID token){
        VerificationEntity verificationEntity = verificationRepository.findByToken(token).orElseThrow(VerificationNotFound::new);
        return verificationMapper.fromEntity(verificationEntity);
    }

    public void save(VerificationDto verificationDto){
        verificationRepository.save(verificationMapper.fromDto(verificationDto));
    }

    public void delete(VerificationDto verificationDto){
        verificationRepository.delete(verificationMapper.fromDto(verificationDto));
    }
}

