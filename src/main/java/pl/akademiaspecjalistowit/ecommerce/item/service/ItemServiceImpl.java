package pl.akademiaspecjalistowit.ecommerce.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.mapper.CategoryMapper;
import pl.akademiaspecjalistowit.ecommerce.category.service.CategoryService;
import pl.akademiaspecjalistowit.ecommerce.category.service.CategoryServiceImpl;
import pl.akademiaspecjalistowit.ecommerce.item.dto.ItemInput;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemDataService itemDataService;
    private final CategoryService categoryService;
    private final WarehouseService warehouseService;

    @Override
    public List<ItemEntity> findByCategoryId(Long categoryId) {
        return itemDataService.findByCategoryId(categoryId);
    }

    @Override
    public List<ItemEntity> findAll() {
        return itemDataService.findAll();
    }

    @Override
    public List<Item> getAllItemsWithAmountAndCategory() {
        return ItemMapper.fromItemDto(itemDataService.findAllItemsWithAmount());
    }

    @Override
    public void registerItem(AddItemRequest addItemRequest) {
        ItemEntity item = processItemInput(ItemMapper.fromGeneratedItemRequest(addItemRequest));
        itemDataService.save(item);
        warehouseService.processNewItem(item, addItemRequest.getItem().getAmount());
    }


    private ItemEntity processItemInput(ItemInput itemInput){
        UUID technicalId = UUID.randomUUID();

        return new ItemEntity(
                technicalId,
                itemInput.getDescription(),
                categoryService.processStringInput(itemInput.getCategory()),
                itemInput.getName(),
                ItemAvailability.AVAILABLE.toString(),
                itemInput.getPrice()
        );
    }

}
