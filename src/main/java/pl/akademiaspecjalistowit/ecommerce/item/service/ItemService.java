package pl.akademiaspecjalistowit.ecommerce.item.service;

import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;
import pl.akademiaspecjalistowit.model.UpdateItemPriceRequest;

import java.util.List;

public interface ItemService {

    void registerItem(AddItemRequest addItemRequest);

    List<Item> getAllItemFromView();

    void updateItemPrice(UpdateItemPriceRequest updateItemPriceRequest);

    List<Item> getAllItemFromViewByCategory(String category);
}
