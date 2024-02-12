package pl.akademiaspecjalistowit.ecommerce.seller.service;

import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.model.AddSellerRequest;

import java.util.UUID;

public interface SellerService {

    void addSeller(AddSellerRequest addSellerRequest);

    SellerBo findSellerByTechId(UUID technicalId);
}
