package pl.akademiaspecjalistowit.ecommerce.email.service;

import java.util.UUID;

public interface VerificationService {
    UUID generateVerificationToken(String mail);

    String checkVerificationToken(UUID token);


}
