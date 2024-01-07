package pl.akademiaspecjalistowit.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "email")
public class EmailEntity {

    @Id
    @Column(name = "address")
    private String address;

    @Column(name = "is_marketing_allowed")
    private boolean isMarketingAllowed;
}
