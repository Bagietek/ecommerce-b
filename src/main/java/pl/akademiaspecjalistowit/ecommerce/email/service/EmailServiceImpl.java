package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.email.model.EmailBo;
import pl.akademiaspecjalistowit.ecommerce.email.model.EmailInput;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@EnableAsync
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    private final VerificationService verificationService;

    @Override
    @Async
    public void sendActivationMail(EmailInput emailInput){
        // todo: emailInput, email from openAPI Presentation layer -> emailBo Business layer -> emailEntity Data layer,
        //  validation in constructor of business layer
        EmailBo emailBo = new EmailBo(emailInput.getEmail());
        SimpleMailMessage message = createActivationMessage(emailBo.getEmail());
        javaMailSender.send(message);
    }

    @Override
    @Async
    public void sendConfirmationMail(UUID token) {
        String email = verificationService.checkVerificationToken(token);
        SimpleMailMessage message = createConfirmationMessage(email);
        javaMailSender.send(message);
    }

    private SimpleMailMessage createConfirmationMessage(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Aktywacja konta");
        message.setText("Witaj! Pomyślnie aktywowano konto.");
        return message;
    }

    private SimpleMailMessage createActivationMessage(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        String verificationLink = "localhost:8080/email/verify/" + verificationService.generateVerificationToken(to).toString();
        message.setTo(to);
        message.setSubject("Aktywacja konta");
        message.setText("Witaj! Aby aktywować konto, kliknij poniższy link:\n" + verificationLink);
        return message;
    }
}
