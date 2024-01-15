package pl.akademiaspecjalistowit.ecommerce.user;

import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.user.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.user.model.UserPrincipal;
import pl.akademiaspecjalistowit.ecommerce.user.service.UserDataService;

@Service
@AllArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserDataService userDataServie;

    @Override
    @SneakyThrows
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        log.trace("Load user {}", oAuth2UserRequest);
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        String username = oAuth2User.getAttributes().get("login").toString();
        String providerId = "github";

        Optional<UserEntity> userOptional = userDataServie.findByUsername(username);
        log.trace("User is {}", userOptional);
        UserEntity user = userOptional
                .orElseGet(() -> registerNewUser(oAuth2UserRequest, username, providerId));
        return new UserPrincipal(user.getUsername(), oAuth2User.getAttributes(), user.getAuthorities());
    }

    private UserEntity registerNewUser(OAuth2UserRequest oAuth2UserRequest, String username, String providerId) {
        AuthorityEntity authorityEntity = new AuthorityEntity("ROLE_CLIENT");
        UserEntity user = new UserEntity(
                Set.of(authorityEntity),
                username,
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                providerId);

        return userDataServie.save(user);
    }
}
