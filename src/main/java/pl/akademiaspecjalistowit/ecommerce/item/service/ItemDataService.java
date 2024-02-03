package pl.akademiaspecjalistowit.ecommerce.item.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.exception.ItemNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemRepository;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemViewRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemDataService {
    private final ItemRepository itemRepository;
    private final ItemViewRepository itemViewRepository;


    public List<ItemView> findAllItemsWithAmountByView(){
        return itemViewRepository.findAll();
    }

    public void save(ItemBo item){
        ItemEntity itemEntity = ItemMapper.entityFromBo(item);
        itemRepository.save(itemEntity);
    }

    public ItemBo findByTechId(UUID technicalId){
        return ItemMapper.boFromEntity(
                itemRepository.findByTechnicalId(technicalId).orElseThrow(ItemNotFoundException::new)
        );
    }

    public List<ItemView> findAllItemsWithAmountByCategory(String category){
        return itemViewRepository.findByCategoryName(category);
    }

}
