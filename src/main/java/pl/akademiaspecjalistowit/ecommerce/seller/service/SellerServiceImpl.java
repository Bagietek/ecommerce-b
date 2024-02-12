package pl.akademiaspecjalistowit.ecommerce.seller.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.seller.mapper.SellerMapper;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.model.AddSellerRequest;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService{
    private final SellerDataService sellerDataService;

    @Override
    public void addSeller(AddSellerRequest addSellerRequest) {
        SellerBo sellerBo = SellerMapper.boFromDto(addSellerRequest.getSeller());
        sellerBo.fillSecurityData(addSellerRequest.getSeller().getPassword(),addSellerRequest.getSeller().getEmail());
        sellerDataService.save(sellerBo);
    }

    @Override
    public SellerBo findSellerByTechId(UUID technicalId) {
        return sellerDataService.findByTechId(technicalId);
    }
}
