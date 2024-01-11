package pl.akademiaspecjalistowit.ecommerce.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.user.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDataService {
    private final UserRepository userRepository;

    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

}
