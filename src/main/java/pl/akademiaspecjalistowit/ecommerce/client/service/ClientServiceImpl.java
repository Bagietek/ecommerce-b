package pl.akademiaspecjalistowit.ecommerce.client.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientDataService clientDataService;

    @Override
    public void deleteClient(Long clientId) {
        clientDataService.deleteClient(clientId);
    }

    @Override
    public void addClient(ClientEntity clientEntity) {
        clientDataService.saveClient(clientEntity);
    }

    @Override
    public List<ClientEntity> getAllClients() {
        return clientDataService.getAllClients();
    }

    @Override
    public ClientEntity getClientByEmail(String email) {
        return clientDataService.getClientByEmail(email);
    }

    @Transactional
    public void verifyClient(ClientEntity clientEntity){
        clientEntity.VerifyClient();
        clientDataService.saveClient(clientEntity);
    }
}
