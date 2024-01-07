package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final EmailDataService emailDataService;

    @Override
    public List<EmailEntity> getAllEmails(){
        return emailDataService.getAllEmails();
    }

    @Override
    public void addEmail(EmailEntity emailEntity) {
        emailDataService.saveEmailEntity(emailEntity);
    }
}
