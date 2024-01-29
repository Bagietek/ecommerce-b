package pl.akademiaspecjalistowit.ecommerce.email.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientService;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class VerificationServiceImpl implements VerificationService{
    private final VerificationDataService verificationDataService;
    private final ClientService clientService;


    @Override
    public UUID generateVerificationToken(String mail) {
        UUID token = UUID.randomUUID();
        ClientEntity clientEntity = clientService.getClientByEmail(mail);
        VerificationEntity verificationEntity = new VerificationEntity(token, clientEntity);
        verificationDataService.save(verificationEntity);
        return token;
    }

    @Override
    public String checkVerificationToken(UUID token) {
        VerificationEntity verificationEntity = verificationDataService.findByToken(token);
        String email = verificationEntity.getClient().getEmail();
        clientService.verifyClient(verificationEntity.getClient());
        verificationDataService.delete(verificationEntity);
        return email;
    }



}
