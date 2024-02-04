package pl.akademiaspecjalistowit.ecommerce.client.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.client.currency.CurrencyExchange;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;
import pl.akademiaspecjalistowit.ecommerce.client.mapper.ClientMapper;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.model.AddClientInformationRequest;
import pl.akademiaspecjalistowit.model.AddFundsRequest;
import pl.akademiaspecjalistowit.model.RegisterClientRequest;

import java.util.UUID;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientDataService clientDataService;
    private final CurrencyExchange currencyExchange;

    @Override
    public void addClient(RegisterClientRequest registerClientRequest) {
        ClientBo clientBo = ClientMapper.clientBoFromDto(registerClientRequest.getClient());
        clientBo.fillSecurityData(registerClientRequest.getClient().getPassword(), registerClientRequest.getClient().getEmail());
        clientDataService.saveClient(clientBo);
    }

    @Override
    public void addInformation(AddClientInformationRequest addClientInformationRequest) {
        ClientBo clientBo = clientDataService.getClientBoByTechnicalId(UUID.fromString(addClientInformationRequest.getTechnicalId()));
        clientBo.updateClientInformation(addClientInformationRequest);
        clientDataService.update(clientBo);
    }

    @Override
    public void addFounds(AddFundsRequest addFundsRequest) {
        ClientBo clientBo = clientDataService.getClientBoByEmail(addFundsRequest.getEmail());
        BigDecimal foundsAmount = currencyExchange.exchangeIntoAccountsSelected(
                new BigDecimal(addFundsRequest.getAmount().getValue()),
                clientBo.getAccountCurrency(),
                AccountCurrency.valueOf(addFundsRequest.getAmount().getCurrency())
        );
        clientBo.addFounds(foundsAmount);
        clientDataService.update(clientBo);
    }

    @Override
    public ClientBo getClientBoByEmail(String email) {
        return clientDataService.getClientBoByEmail(email);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientDataService.deleteClient(clientId);
    }

    @Override
    @Transactional
    public void verifyClient(ClientBo clientBo) {
        clientBo.verifyClient();
        clientDataService.update(clientBo);
    }
}
