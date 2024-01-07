package pl.akademiaspecjalistowit.ecommerce.client.service;

import pl.akademiaspecjalistowit.ecommerce.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.entity.EmailEntity;

import java.util.List;

public interface ClientService {

    public List<ClientEntity> getAllClients();

    public void addClient(ClientEntity clientEntity);

    void deleteClient(Long clientId);

    ClientEntity getClientByEmail(EmailEntity emailEntity);
}
