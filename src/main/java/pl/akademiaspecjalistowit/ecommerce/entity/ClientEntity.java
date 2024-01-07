package pl.akademiaspecjalistowit.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "client")
public class ClientEntity {

    public ClientEntity(UUID technicalId, EmailEntity email, String password, String accountCurrency, String accountBalance, String status) {
        this.technicalId = technicalId;
        this.email = email;
        this.password = password;
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "email")
    private EmailEntity email;

    private String password;

    @Column(name = "account_currency")
    private String accountCurrency;

    @Column(name = "account_balance")
    private String accountBalance;

    private String status;
}
