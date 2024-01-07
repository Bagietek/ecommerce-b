package pl.akademiaspecjalistowit.ecommerce.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.repository.ClientRepository;
import pl.akademiaspecjalistowit.ecommerce.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientDataService{
    private final ClientRepository clientRepository;

    public List<ClientEntity> getAllClients(){
        return clientRepository.findAll();
    }

    public void saveClient(ClientEntity clientEntity){
        clientRepository.save(clientEntity);
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public ClientEntity getClientByEmailEntity(EmailEntity emailEntity){
        return clientRepository.getClientEntityByEmail(emailEntity);
    }
}
