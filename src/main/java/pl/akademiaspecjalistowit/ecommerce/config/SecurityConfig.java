package pl.akademiaspecjalistowit.ecommerce.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.akademiaspecjalistowit.ecommerce.user.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        /* todo: security layers, temporarily disabled for postman use
                        .requestMatchers("/guest/items").permitAll()
                        .requestMatchers("/seller/register").hasRole("SELLER")*/
                        //.requestMatchers("/seller/items/add").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(infoEndpoint ->
                                infoEndpoint.userService((customOAuth2UserService))))
                .csrf().disable();
                //todo: csrf in postman
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {


        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user15")
                .password("password")
                .roles("CLIENT")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }*/
}
