package pl.akademiaspecjalistowit.ecommerce.client.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientStatus;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "client")
public class ClientEntity {

    public ClientEntity(UUID technicalId, String accountCurrency, String accountBalance, String status, UserEntity userEntity, String email) {
        this.technicalId = technicalId;
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.status = status;
        this.userEntity = userEntity;
        this.email = email;
    }

    public ClientEntity(UUID technicalId, String accountCurrency, String accountBalance,
                        String status, String email, String name,
                        String surname, AddressEntity addressEntity) {
        this.technicalId = technicalId;
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.status = status;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.addressEntity = addressEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @Column(name = "account_currency")
    private String accountCurrency;

    @Column(name = "account_balance")
    private String accountBalance;

    private String status;

    private String email;

    @OneToOne
    @JoinColumn(name = "user_entity_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private UserEntity userEntity;

    private String name;

    private String surname;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
}
