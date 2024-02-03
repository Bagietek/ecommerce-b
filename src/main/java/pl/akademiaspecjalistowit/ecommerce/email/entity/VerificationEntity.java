package pl.akademiaspecjalistowit.ecommerce.email.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "verification_data")
public class VerificationEntity {

    public VerificationEntity(UUID token, ClientEntity client){
        this.token = token;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "token")
    private UUID token;

    @JoinColumn(name = "client_id")
    @OneToOne
    private ClientEntity client;
}
