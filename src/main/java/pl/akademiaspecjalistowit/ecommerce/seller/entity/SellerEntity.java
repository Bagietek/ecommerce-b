package pl.akademiaspecjalistowit.ecommerce.seller.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.entity.AddressEntity;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerStatus;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "seller")
public class SellerEntity {

    public SellerEntity(UUID technicalId, UserEntity userEntity, String companyName, String email, AddressEntity addressEntity, String status) {
        this.technicalId = technicalId;
        this.userEntity = userEntity;
        this.companyName = companyName;
        this.email = email;
        this.addressEntity = addressEntity;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @Column(name = "company_name")
    private String companyName;

    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    private String status;
}
