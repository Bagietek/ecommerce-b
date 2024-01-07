package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;
import pl.akademiaspecjalistowit.ecommerce.email.repository.EmailRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailDataService {
    private final EmailRepository emailRepository;

    public List<EmailEntity> getAllEmails(){
        return emailRepository.findAll();
    }

    public void saveEmailEntity(EmailEntity emailEntity){
            emailRepository.save(emailEntity);
    }
}
