package pl.akademiaspecjalistowit.ecommerce.email.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.service.ClientService;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.email.model.VerificationBo;

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
        ClientBo clientBo = clientService.getClientBoByEmail(mail);
        VerificationBo verificationBo = new VerificationBo(clientBo, token);
        verificationDataService.save(verificationBo);
        return token;
    }

    @Override
    public String checkVerificationToken(UUID token) {
        VerificationBo verificationBo = verificationDataService.findByToken(token);
        String email = verificationBo.getClientBo().getEmail();
        clientService.verifyClient(verificationBo.getClientBo());
        verificationDataService.delete(verificationBo);
        return email;
    }
}
