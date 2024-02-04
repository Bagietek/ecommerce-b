package pl.akademiaspecjalistowit.ecommerce.client.mapper;

import pl.akademiaspecjalistowit.ecommerce.client.entity.AddressEntity;
import pl.akademiaspecjalistowit.ecommerce.client.model.AddressBo;
import pl.akademiaspecjalistowit.model.Address;

import java.util.Optional;

public class AddressMapper {

    public static AddressBo boFromEntity(AddressEntity addressEntity){
        if(addressEntity == null){
            return  null;
        }

        return new AddressBo(
                addressEntity.getId(),
                addressEntity.getCity(),
                addressEntity.getStreet(),
                addressEntity.getPostalCode(),
                addressEntity.getHouseNumber(),
                addressEntity.getFlatNumber()
        );
    }

    public static AddressEntity entityFromBo(AddressBo addressBo){
        if(addressBo == null){
            return null;
        }

        return new AddressEntity(
                addressBo.getId(),
                addressBo.getCity(),
                addressBo.getStreet(),
                addressBo.getPostalCode(),
                addressBo.getHouseNumber(),
                addressBo.getFlatNumber()
        );
    }

    public static Address addressFromBo(AddressBo addressBo){
        if(addressBo == null){
            return null;
        }

        Address address = new Address();
        address.setTown(addressBo.getCity());
        address.setFlatNumber(addressBo.getFlatNumber());
        address.setStreet(addressBo.getStreet());
        address.setHouseNumber(addressBo.getHouseNumber());
        address.setPostalCode(addressBo.getPostalCode());
        return address;
    }


    public static AddressBo boFromAddress(Address address) {
        if(address == null){
            return null;
        }
        return new AddressBo(
                address.getTown(),
                address.getStreet(),
                address.getPostalCode(),
                address.getHouseNumber(),
                address.getFlatNumber()
        );
    }
}
