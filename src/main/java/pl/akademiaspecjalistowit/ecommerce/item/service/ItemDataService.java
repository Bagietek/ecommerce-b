package pl.akademiaspecjalistowit.ecommerce.item.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.dto.ItemDto;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.exception.ItemNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemRepository;
import pl.akademiaspecjalistowit.ecommerce.item.repository.ItemViewRepository;
import pl.akademiaspecjalistowit.model.Item;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemDataService {
    private final ItemRepository itemRepository;
    private final ItemViewRepository itemViewRepository;


    public List<ItemDto> findAllItemsWithAmount(){

        List<Map<String, Object>> result = itemRepository.findAllItemsWithAmount(PageRequest.of(0,5));

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

    public List<ItemView> findAllItemsWithAmountByView(){
        return itemViewRepository.findAll();
    }

    public void save(ItemEntity item){
        itemRepository.save(item);
    }

    public List<ItemView> findAllItemsWithAmountByCategory(String category){
        return itemViewRepository.findByCategoryName(category);
    }

}
