package pl.akademiaspecjalistowit.ecommerce.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemDataService {
    private final ItemRepository itemRepository;

    public List<ItemEntity> findByCategoryId(Long categoryId){
        return itemRepository.findItemEntitiesByCategoryId(categoryId);
    }
}
