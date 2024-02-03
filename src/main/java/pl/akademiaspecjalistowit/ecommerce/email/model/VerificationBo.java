package pl.akademiaspecjalistowit.ecommerce.email.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VerificationBo {
    public VerificationBo(ClientBo clientBo, UUID token){
        this.clientBo = clientBo;
        this.token = token;
    }

    private Long id;
    private ClientBo clientBo;
    private UUID token;
}
