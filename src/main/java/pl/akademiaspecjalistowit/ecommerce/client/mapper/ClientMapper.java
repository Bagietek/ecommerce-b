package pl.akademiaspecjalistowit.ecommerce.client.mapper;

import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientStatus;
import pl.akademiaspecjalistowit.model.Client;

import java.util.Optional;

public class ClientMapper {

    public static ClientEntity entityFromBo(ClientBo clientBo){
        return new ClientEntity(
                clientBo.getId(),
                clientBo.getTechnicalId(),
                clientBo.getAccountCurrency().toString(),
                clientBo.getAccountBalance(),
                clientBo.getStatus().toString(),
                clientBo.getEmail(),
                clientBo.getUserEntityId(),
                clientBo.getName(),
                clientBo.getSurname(),
                AddressMapper.entityFromBo(clientBo.getAddressBo())
        );
    }

    public static ClientBo boFromEntity(ClientEntity clientEntity){
        return new ClientBo(
                clientEntity.getId(),
                clientEntity.getTechnicalId(),
                AccountCurrency.valueOf(clientEntity.getAccountCurrency()),
                clientEntity.getAccountBalance(),
                ClientStatus.valueOf(clientEntity.getStatus()),
                clientEntity.getEmail(),
                clientEntity.getName(),
                clientEntity.getSurname(),
                AddressMapper.boFromEntity(clientEntity.getAddressEntity()),
                clientEntity.getUserEntity()
        );
    }

    public static Client clientFromBo(ClientEntity clientEntity){
        Client client = new Client();
        client.setAddress(AddressMapper.addressFromBo(AddressMapper.boFromEntity(clientEntity.getAddressEntity())));
        client.setEmail(clientEntity.getEmail());
        client.setName(clientEntity.getName());
        client.setSurname(clientEntity.getSurname());
        return client;
    }

    public static ClientBo clientBoFromDto(Client client){
        if(client == null){
            return null;
        }
        return new ClientBo(
                client.getEmail(),
                client.getName(),
                client.getSurname(),
                AddressMapper.boFromAddress(client.getAddress())
        );
    }
}
