package pl.akademiaspecjalistowit.ecommerce.email.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VerificationDto {
    public VerificationDto(ClientEntity clientEntity, UUID token){
        this.clientEntity = clientEntity;
        this.token = token;
    }

    private Long id;
    private ClientEntity clientEntity;
    private UUID token;
}
