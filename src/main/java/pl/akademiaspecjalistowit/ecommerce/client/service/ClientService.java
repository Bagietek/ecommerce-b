package pl.akademiaspecjalistowit.ecommerce.client.service;

import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.model.AddClientInformationRequest;
import pl.akademiaspecjalistowit.model.AddFundsRequest;
import pl.akademiaspecjalistowit.model.RegisterClientRequest;

import java.util.List;

public interface ClientService {

    void addInformation(AddClientInformationRequest addClientInformationRequest);
    void addFounds(AddFundsRequest addFundsRequest);
    ClientBo getClientBoByEmail(String email);
    void deleteClient(Long clientId);

    void verifyClient(ClientBo clientBo);

    void addClient(RegisterClientRequest registerClientRequest);
}
