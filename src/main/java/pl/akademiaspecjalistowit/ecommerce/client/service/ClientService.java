package pl.akademiaspecjalistowit.ecommerce.client.service;

import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;

import java.util.List;

public interface ClientService {

    public List<ClientEntity> getAllClients();

    public void addClient(ClientEntity clientEntity);

    ClientBo getClientBoByEmail(String email);
    void deleteClient(Long clientId);

    void verifyClient(ClientBo clientBo);
}
