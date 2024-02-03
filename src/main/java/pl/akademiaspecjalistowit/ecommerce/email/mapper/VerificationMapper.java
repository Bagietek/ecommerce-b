package pl.akademiaspecjalistowit.ecommerce.email.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.mapper.ClientMapper;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;
import pl.akademiaspecjalistowit.ecommerce.email.model.VerificationBo;

import java.util.Optional;

@NoArgsConstructor
@Service
public class VerificationMapper {

    public VerificationBo fromEntity(VerificationEntity verificationEntity){
        return new VerificationBo(
                verificationEntity.getId(),
                ClientMapper.boFromEntity(verificationEntity.getClient()),
                verificationEntity.getToken()
        );
    }

    public VerificationEntity fromBo(VerificationBo verificationBo){
        if(Optional.ofNullable(verificationBo.getId()).isPresent()){
            return new VerificationEntity(
                    verificationBo.getId(),
                    verificationBo.getToken(),
                    ClientMapper.entityFromBo(verificationBo.getClientBo())
            );
        }

        return new VerificationEntity(
                verificationBo.getToken(),
                ClientMapper.entityFromBo(verificationBo.getClientBo())
        );
    }
}
