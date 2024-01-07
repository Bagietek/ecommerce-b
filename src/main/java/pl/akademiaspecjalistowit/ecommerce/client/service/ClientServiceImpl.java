package pl.akademiaspecjalistowit.ecommerce.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;

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
    public ClientEntity getClientByEmail(EmailEntity emailEntity) {
        return clientDataService.getClientByEmailEntity(emailEntity);
    }
}
