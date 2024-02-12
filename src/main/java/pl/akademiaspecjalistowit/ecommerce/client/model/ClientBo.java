package pl.akademiaspecjalistowit.ecommerce.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientFundsException;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientValidationException;
import pl.akademiaspecjalistowit.ecommerce.client.mapper.AddressMapper;
import pl.akademiaspecjalistowit.ecommerce.user.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.model.AddClientInformationRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientBo {
    public ClientBo(String email, String name, String surname, AddressBo addressBo) {
        verifyEmail(email);
        this.accountCurrency = AccountCurrency.PLN;
        this.accountBalance = "0";
        this.status = ClientStatus.NOT_ACTIVATED;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.addressBo = addressBo;
    }

    private Long id;
    private UUID technicalId;
    private AccountCurrency accountCurrency;
    private String accountBalance;
    private ClientStatus status;
    private String email;
    private String name;
    private String surname;
    private AddressBo addressBo;
    private UserEntity userEntityId;

    public void verifyClient(){
        this.status = ClientStatus.ACTIVATED;
        this.userEntityId.setAuthorities(this.grantAuthorities());
    }

    public void addFounds(BigDecimal amount){
        BigDecimal currentBalance = new BigDecimal(this.accountBalance);
        currentBalance = currentBalance.add(amount);
        currentBalance = currentBalance.setScale(2, RoundingMode.HALF_DOWN);
        this.accountBalance = currentBalance.toPlainString();
    }

    public void updateClientInformation(AddClientInformationRequest addClientInformationRequest){
        this.name = addClientInformationRequest.getName();
        this.surname = addClientInformationRequest.getSurname();
        this.addressBo = AddressMapper.boFromAddress(addClientInformationRequest.getAddress());
    }

    private void grantNewTechnicalId(){
        this.technicalId = UUID.randomUUID();
    }

    public void fillSecurityData(String password, String username){
        grantNewTechnicalId();
        this.userEntityId = new UserEntity(
                null, //grantAuthorities()
                hashPassword(password),
                username
        );
    }
    private Set<AuthorityEntity> grantAuthorities(){
        Set<AuthorityEntity> authorityEntitySet = new HashSet<>();
        authorityEntitySet.add(new AuthorityEntity(
                "ROLE_CLIENT"
        ));
        return authorityEntitySet;
    }

    private String hashPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    private void detractFounds(BigDecimal summedOrder) {
        BigDecimal currentBalance = new BigDecimal(this.accountBalance);
        currentBalance = currentBalance.subtract(summedOrder);
        currentBalance = currentBalance.setScale(2, RoundingMode.HALF_DOWN);
        this.accountBalance = currentBalance.toPlainString();
    }

    public void updateClientFunds(BigDecimal summedOrder, BigDecimal exchangedClientFunds){
        detractFounds(summedOrder);
    }

    private void verifyEmail(String email){
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(!email.matches(emailPattern)){
            throw new ClientValidationException();
        }
    }
}
