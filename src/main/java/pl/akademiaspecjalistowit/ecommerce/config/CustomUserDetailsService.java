package pl.akademiaspecjalistowit.ecommerce.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientDataService;
import pl.akademiaspecjalistowit.ecommerce.entity.ClientEntity;

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
        return new User(
                clientEntity.getEmail(),
                clientEntity.getPassword(),
                AuthorityUtils.createAuthorityList("CLIENT")
                );
    }
}
