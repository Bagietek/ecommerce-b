package pl.akademiaspecjalistowit.ecommerce.item.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemDataService {
    private final ItemRepository itemRepository;
    private final ItemViewRepository itemViewRepository;


    public List<ItemView> findAllItemsWithAmountByView(){
        return itemViewRepository.findAll();
    }

    public List<ItemView> getItemsFromSearch(BigDecimal minPrice, BigDecimal maxPrice, String category, BigDecimal page, BigDecimal pageSize){
        PageRequest pageable = getDefaultSearchPagination();
        BigDecimal minimalPrice = BigDecimal.ZERO;
        BigDecimal maximumPrice = BigDecimal.valueOf(Long.MAX_VALUE);
        if(validateSearchPage(page, pageSize)){
            pageable = PageRequest.of(page.intValueExact(),pageSize.intValueExact(), Sort.by("name"));
        }
        if(!validateMaxPrice(maxPrice)){
            maximumPrice = maxPrice;
        }
        if(!validateMinPrice(minPrice)){
            minimalPrice = minPrice;
        }

        return itemViewRepository.itemSearch(minimalPrice, maximumPrice, category, pageable);
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

    // false is passed validation
    private Boolean validateSearchPage(BigDecimal page, BigDecimal pageSize){
        // page >= 0, page size > 0
        return page.compareTo(BigDecimal.ZERO) >= 0 && pageSize.compareTo(BigDecimal.ZERO) > 0;
    }

    private Boolean validateMaxPrice(BigDecimal maxPrice){
        if(maxPrice == null){
            return true;
        }
        return maxPrice.compareTo(BigDecimal.ZERO) <= 0;
    }

    private Boolean validateMinPrice(BigDecimal minPrice){
        if(minPrice == null){
            return true;
        }
        return minPrice.compareTo(BigDecimal.ZERO) < 0;
    }

    private PageRequest getDefaultSearchPagination(){
        return PageRequest.of(0,5, Sort.by("name"));
    }
}
