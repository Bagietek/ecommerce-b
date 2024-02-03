package pl.akademiaspecjalistowit.ecommerce.client.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.client.mapper.ClientMapper;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientDataService clientDataService;

    @Override
    public ClientBo getClientBoByEmail(String email) {
        return clientDataService.getClientBoByEmail(email);
    }

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
    @Transactional
    public void verifyClient(ClientBo clientBo) {
        clientBo.verifyClient();
        clientDataService.update(clientBo);
    }
}
