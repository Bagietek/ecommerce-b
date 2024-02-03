package pl.akademiaspecjalistowit.ecommerce.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspecjalistowit.ecommerce.email.model.EmailInput;
import pl.akademiaspecjalistowit.ecommerce.email.service.EmailService;
import pl.akademiaspecjalistowit.ecommerce.email.service.VerificationService;

import java.util.UUID;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/verify")
    public void sendVerificationMail(@RequestBody EmailInput email){
        emailService.sendActivationMail(email);
    }

    @GetMapping("/verify/{token}")
    public void checkVerificationToken(@PathVariable UUID token){
        emailService.sendConfirmationMail(token);
    }
}
