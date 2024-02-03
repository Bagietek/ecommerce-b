package pl.akademiaspecjalistowit.ecommerce.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.client.mapper.ClientMapper;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.repository.ClientRepository;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;

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

    public ClientEntity getClientByEmail(String email){
        return clientRepository.getClientEntityByEmail(email).orElseThrow(ClientNotFoundException::new);
    }

    public ClientBo getClientBoByEmail(String email){
        ClientEntity clientEntity = clientRepository.getClientEntityByEmail(email).orElseThrow(ClientNotFoundException::new);
        return ClientMapper.boFromEntity(clientEntity);
    }

    public void update(ClientBo client){
        clientRepository.save(ClientMapper.entityFromBo(client));
    }
}
