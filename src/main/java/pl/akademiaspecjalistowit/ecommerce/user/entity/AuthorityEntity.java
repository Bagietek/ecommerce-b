package pl.akademiaspecjalistowit.ecommerce.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "authority_entity")
public class AuthorityEntity implements GrantedAuthority {

    public AuthorityEntity(String authority) {
        this.authority = authority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
