package pl.akademiaspecjalistowit.ecommerce.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.dto.ItemDto;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.exception.ItemNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemDataService {
    private final ItemRepository itemRepository;

    public List<ItemEntity> findByCategoryId(Long categoryId){
        return itemRepository.findItemEntitiesByCategoryId(categoryId);
    }

    public List<ItemEntity> findAll(){
        return itemRepository.findAll();
    }

    public List<ItemDto> findAllItemsWithAmount(){

        List<Map<String, Object>> result = itemRepository.findAllItemsWithAmount();

        return result.stream()
                .map(row -> {
                    ItemDto itemDto = new ItemDto();
                    itemDto.setName((String) row.get("name"));
                    itemDto.setDescription((String) row.get("description"));
                    itemDto.setPrice((BigDecimal) row.get("price"));
                    itemDto.setCategory((String) row.get("categoryName"));
                    itemDto.setNumber_of_products((long) row.get("numberOfProducts"));
                    return itemDto;
                })
                .collect(Collectors.toList());
    }

    public void save(ItemEntity item){
        itemRepository.save(item);
    }
}
