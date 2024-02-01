package pl.akademiaspecjalistowit.ecommerce.email.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientService;
import pl.akademiaspecjalistowit.ecommerce.email.entity.VerificationEntity;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.email.model.VerificationDto;

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
        // todo: client
        ClientEntity clientEntity = clientService.getClientByEmail(mail);
        // todo: Verification
        VerificationDto verificationDto = new VerificationDto(clientEntity, token);
        // todo: Verification, pom openapi Dto suffix / Business object
        verificationDataService.save(verificationDto);
        return token;
    }

    @Override
    public String checkVerificationToken(UUID token) {
        VerificationDto verificationDto = verificationDataService.findByToken(token);
        String email = verificationDto.getClientEntity().getEmail();
        clientService.verifyClient(verificationDto.getClientEntity());
        // verificationDto?
        verificationDataService.delete(verificationDto);
        return email;
    }



}
