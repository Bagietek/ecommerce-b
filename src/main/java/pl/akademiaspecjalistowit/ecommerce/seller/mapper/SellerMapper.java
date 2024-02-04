package pl.akademiaspecjalistowit.ecommerce.seller.mapper;

import pl.akademiaspecjalistowit.ecommerce.client.mapper.AddressMapper;
import pl.akademiaspecjalistowit.ecommerce.seller.entity.SellerEntity;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerStatus;
import pl.akademiaspecjalistowit.model.Seller;

public class SellerMapper {

    public static SellerEntity entityFromBo(SellerBo sellerBo){
        if (sellerBo == null){
            return null;
        }
        return new SellerEntity(
                sellerBo.getId(),
                sellerBo.getTechnicalId(),
                sellerBo.getUserEntity(),
                sellerBo.getCompanyName(),
                sellerBo.getEmail(),
                AddressMapper.entityFromBo(sellerBo.getAddressBo()),
                sellerBo.getSellerStatus().toString()
        );
    }

    public static SellerBo boFromEntity(SellerEntity sellerEntity){
        if (sellerEntity == null){
            return null;
        }
        return new SellerBo(
                sellerEntity.getId(),
                sellerEntity.getTechnicalId(),
                sellerEntity.getUserEntity(),
                sellerEntity.getCompanyName(),
                sellerEntity.getEmail(),
                AddressMapper.boFromEntity(sellerEntity.getAddressEntity()),
                SellerStatus.valueOf(sellerEntity.getStatus())
        );
    }

    public static SellerBo boFromDto(Seller seller){
        if (seller == null){
            return null;
        }
        return new SellerBo(
                seller.getCompanyName(),
                seller.getEmail(),
                AddressMapper.boFromAddress(seller.getAddress())
        );
    }
}
