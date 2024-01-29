package pl.akademiaspecjalistowit.ecommerce.email.service;

import java.util.UUID;

public interface EmailService {
    void sendActivationMail(String to);

    void sendConfirmationMail(UUID token);
}
