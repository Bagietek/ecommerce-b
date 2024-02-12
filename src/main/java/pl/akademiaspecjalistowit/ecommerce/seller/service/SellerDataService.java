package pl.akademiaspecjalistowit.ecommerce.seller.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.seller.exception.SellerNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.seller.mapper.SellerMapper;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.ecommerce.seller.repository.SellerRepository;

import java.util.UUID;

@AllArgsConstructor
@Service
public class SellerDataService {
    private final SellerRepository sellerRepository;

    public void save(SellerBo sellerBo){
        sellerRepository.save(SellerMapper.entityFromBo(sellerBo));
    }

    public SellerBo findByTechId(UUID technicalId) {
        return SellerMapper.boFromEntity(
                sellerRepository.findByTechnicalId(technicalId).orElseThrow(SellerNotFoundException::new)
        );
    }
}
