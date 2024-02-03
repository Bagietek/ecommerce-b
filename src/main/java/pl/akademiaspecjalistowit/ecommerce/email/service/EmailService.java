package pl.akademiaspecjalistowit.ecommerce.email.service;

import pl.akademiaspecjalistowit.ecommerce.email.model.EmailInput;

import java.util.UUID;

public interface EmailService {
    void sendActivationMail(EmailInput emailInput);

    void sendConfirmationMail(UUID token);
}
