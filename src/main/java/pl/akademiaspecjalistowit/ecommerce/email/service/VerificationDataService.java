package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.email.exception.VerificationNotFound;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;
import pl.akademiaspecjalistowit.ecommerce.email.mapper.VerificationMapper;
import pl.akademiaspecjalistowit.ecommerce.email.model.VerificationBo;
import pl.akademiaspecjalistowit.ecommerce.email.repository.VerificationRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VerificationDataService {
    private final VerificationRepository verificationRepository;
    private final VerificationMapper verificationMapper;

    public VerificationBo findByToken(UUID token){
        VerificationEntity verificationEntity = verificationRepository.findByToken(token).orElseThrow(VerificationNotFound::new);
        return verificationMapper.fromEntity(verificationEntity);
    }

    public void save(VerificationBo verificationBo){
        verificationRepository.save(verificationMapper.fromBo(verificationBo));
    }

    public void delete(VerificationBo verificationBo){
        verificationRepository.delete(verificationMapper.fromBo(verificationBo));
    }
}

