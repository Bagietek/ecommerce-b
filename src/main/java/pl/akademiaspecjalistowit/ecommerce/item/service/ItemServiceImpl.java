package pl.akademiaspecjalistowit.ecommerce.item.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.mapper.CategoryMapper;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;
import pl.akademiaspecjalistowit.ecommerce.category.service.CategoryService;
import pl.akademiaspecjalistowit.ecommerce.category.service.CategoryServiceImpl;
import pl.akademiaspecjalistowit.ecommerce.item.dto.ItemInput;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;
import pl.akademiaspecjalistowit.model.UpdateItemPriceRequest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemDataService itemDataService;
    private final CategoryService categoryService;
    private final WarehouseService warehouseService;

    @Override
    public List<Item> getAllItemFromViewByCategory(String category) {
        return itemDataService.findAllItemsWithAmountByCategory(category)
                .stream()
                .map(ItemMapper::itemFromItemView)
                .collect(Collectors.toList());
    }

    public List<Item> getAllItemFromView(){
        return itemDataService.findAllItemsWithAmountByView()
                .stream()
                .map(ItemMapper::itemFromItemView)
                .collect(Collectors.toList());
    }

    @Override
    public void registerItem(AddItemRequest addItemRequest) {
        ItemBo itemBo = processItemInput(addItemRequest.getItem());
        warehouseService.processNewItem(itemBo, addItemRequest.getItem().getAmount());
    }

    @Override
    @Transactional
    public void updateItemPrice(UpdateItemPriceRequest updateItemPriceRequest) {
        ItemBo itemBo = itemDataService.findByTechId(UUID.fromString(updateItemPriceRequest.getTechnicalId()));
        itemBo.updatePrice(new BigDecimal(updateItemPriceRequest.getPrice()));
        itemDataService.save(itemBo);
    }

    private ItemBo processItemInput(Item item){
        UUID technicalId = UUID.randomUUID();
        //categoryService.processStringInput(item.getCategory())
        return new ItemBo(
                technicalId,
                item.getDescription(),
                new CategoryBo(item.getCategory()),
                item.getName(),
                ItemAvailability.AVAILABLE,
                item.getPrice()
        );
    }

}
