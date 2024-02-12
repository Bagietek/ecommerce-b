package pl.akademiaspecjalistowit.ecommerce.item.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;
import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.ecommerce.seller.service.SellerService;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;
import pl.akademiaspecjalistowit.model.UpdateItemPriceRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemDataService itemDataService;
    private final WarehouseService warehouseService;

    @Override
    public ItemBo findItemByTechId(UUID technicalId) {
        return itemDataService.findByTechId(technicalId);
    }

    @Override
    public List<Item> getItemsFromSearch(BigDecimal minPrice, BigDecimal maxPrice, String category, BigDecimal page, BigDecimal pageSize) {
        return itemDataService.getItemsFromSearch(minPrice, maxPrice, category, page, pageSize)
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
    public void deleteItem(String itemTechnicalId, String sellerTechnicalId) {
        ItemBo itemBo = itemDataService.findByTechId(UUID.fromString(itemTechnicalId));
        warehouseService.deleteByItem(itemBo, sellerTechnicalId);
    }

    @Override
    public void registerItem(AddItemRequest addItemRequest) {
        ItemBo itemBo = processItemInput(addItemRequest.getItem());
        warehouseService.processNewItem(itemBo, Long.valueOf(addItemRequest.getItem().getAmount()), addItemRequest.getSellerTechId());
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
