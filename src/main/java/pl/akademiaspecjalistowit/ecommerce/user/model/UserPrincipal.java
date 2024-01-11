package pl.akademiaspecjalistowit.ecommerce.user.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPrincipal implements OAuth2User, Serializable {

    private String email;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> grantedAuthoritySet;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthoritySet;
    }

    @Override
    public String getName() {
        return email;
    }
}
