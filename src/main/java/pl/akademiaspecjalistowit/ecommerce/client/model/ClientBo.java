package pl.akademiaspecjalistowit.ecommerce.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientBo {
    public ClientBo(Long id, UUID technicalId, String accountCurrency, String accountBalance, ClientStatus status, String email) {
        this.id = id;
        this.technicalId = technicalId;
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.status = status;
        this.email = email;
    }

    private Long id;
    private UUID technicalId;
    private String accountCurrency;
    private String accountBalance;
    private ClientStatus status;
    private String email;
    private String name;
    private String surname;
    private AddressBo addressBo;
    private UserEntity userEntityId;


    public void verifyClient(){
        this.status = ClientStatus.ACTIVATED;
    }
}
