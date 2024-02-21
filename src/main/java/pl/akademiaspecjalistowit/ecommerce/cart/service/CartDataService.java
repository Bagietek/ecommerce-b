package pl.akademiaspecjalistowit.ecommerce.cart.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.cart.entity.CartEntity;
import pl.akademiaspecjalistowit.ecommerce.cart.mapper.CartMapper;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartBo;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartView;
import pl.akademiaspecjalistowit.ecommerce.cart.repository.CartRepository;
import pl.akademiaspecjalistowit.ecommerce.cart.repository.CartViewRepository;
import pl.akademiaspecjalistowit.ecommerce.client.exception.ClientNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.client.mapper.ClientMapper;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartDataService {
    private final CartRepository cartRepository;
    private final CartViewRepository cartViewRepository;

    public void save(CartBo cartBo){
        CartEntity cartEntity = CartMapper.entityFromBo(cartBo);
        cartRepository.save(cartEntity);
    }

    public void deleteCart(List<CartBo> cartBos){
        cartRepository.deleteAll(cartBos.stream()
                .map(CartMapper::entityFromBo)
                .collect(Collectors.toSet())
        );
    }

    public List<CartBo> getClientCartBo(ClientBo clientBo){
        return cartRepository.getAllByClientId(ClientMapper.entityFromBo(clientBo))
                .stream()
                .map(CartMapper::boFromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteByTechnicalId(UUID technicalId){
        cartRepository.deleteByTechnicalId(technicalId);
    }

    @Transactional
    void deleteAllByClient(ClientBo clientBo){
        cartRepository.deleteAllByClientId(ClientMapper.entityFromBo(clientBo));
    }

    public Integer getReservedAmount(ItemBo itemBo){
        List<CartEntity> allItems = cartRepository.getAllByItems(ItemMapper.entityFromBo(itemBo));
        long reservedAmount = allItems.stream()
                .mapToLong(CartEntity::getNumberOfProducts)
                .sum();
        return Math.toIntExact(reservedAmount);
    }

    public List<CartView> getClientCartView(UUID clientTechnicalId){
        return cartViewRepository.findByClientId(clientTechnicalId);
    }
}
