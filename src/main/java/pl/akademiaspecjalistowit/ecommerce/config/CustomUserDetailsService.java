package pl.akademiaspecjalistowit.ecommerce.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientDataService;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final ClientDataService clientDataService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientEntity clientEntity = clientDataService.getClientByEmail(username);
        if(clientEntity == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<String> authorities = clientEntity.getUserEntity().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return new User(
                clientEntity.getUserEntity().getUsername(),
                clientEntity.getUserEntity().getPassword(),
                AuthorityUtils.createAuthorityList(authorities)
                );
    }
}
