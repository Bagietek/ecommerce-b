package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    private final VerificationService verificationService;

    @Override
    public void sendActivationMail(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        String verificationLink = "localhost:8080/email/verify/" + verificationService.generateVerificationToken(to).toString();
        message.setTo(to);
        message.setSubject("Aktywacja konta");
        message.setText("Witaj! Aby aktywować konto, kliknij poniższy link:\n" + verificationLink);
        javaMailSender.send(message);
    }

    @Override
    public void sendConfirmationMail(UUID token) {
        String email = verificationService.checkVerificationToken(token);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Aktywacja konta");
        message.setText("Witaj! Pomyślnie aktywowano konto.");
        javaMailSender.send(message);
    }
}
