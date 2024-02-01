package pl.akademiaspecjalistowit.ecommerce.item.service;

import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItemsWithAmountAndCategory();

    void registerItem(AddItemRequest addItemRequest);

    List<ItemView> getAllItemFromView();

    List<ItemView> getAllItemFromViewByCategory(String category);
}
