package pl.akademiaspecjalistowit.ecommerce.item.service;

import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;
import pl.akademiaspecjalistowit.model.UpdateItemPriceRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ItemService {

    List<Item> getItemsFromSearch(BigDecimal minPrice, BigDecimal maxPrice, String category, BigDecimal page, BigDecimal pageSize);
    void registerItem(AddItemRequest addItemRequest);

    List<Item> getAllItemFromView();

    void updateItemPrice(UpdateItemPriceRequest updateItemPriceRequest);

    List<Item> getAllItemFromViewByCategory(String category);

    void deleteItem(String itemTechnicalId, String sellerTechnicalId);

    ItemBo findItemByTechId(UUID technicalId);
}
