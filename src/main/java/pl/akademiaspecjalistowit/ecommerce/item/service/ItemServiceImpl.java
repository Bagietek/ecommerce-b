package pl.akademiaspecjalistowit.ecommerce.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.entity.ItemEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemDataService itemDataService;

    @Override
    public List<ItemEntity> findByCategoryId(Long categoryId) {
        return itemDataService.findByCategoryId(categoryId);
    }
}
