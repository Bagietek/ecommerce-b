package pl.akademiaspecjalistowit.ecommerce.email.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;
import pl.akademiaspecjalistowit.ecommerce.email.model.VerificationDto;

import java.util.Optional;

@NoArgsConstructor
@Service
public class VerificationMapper {

    public VerificationDto fromEntity(VerificationEntity verificationEntity){
        return new VerificationDto(
                verificationEntity.getId(),
                verificationEntity.getClient(),
                verificationEntity.getToken()
        );
    }

    public VerificationEntity fromDto(VerificationDto verificationDto){
        if(Optional.ofNullable(verificationDto.getId()).isPresent()){
            return new VerificationEntity(
                    verificationDto.getId(),
                    verificationDto.getToken(),
                    verificationDto.getClientEntity()
            );
        }

        return new VerificationEntity(
                verificationDto.getToken(),
                verificationDto.getClientEntity()
        );
    }
}
