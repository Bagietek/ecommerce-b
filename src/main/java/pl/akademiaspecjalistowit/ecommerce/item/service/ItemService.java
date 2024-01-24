package pl.akademiaspecjalistowit.ecommerce.item.service;

import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;

import java.util.List;

public interface ItemService {

    List<ItemEntity> findByCategoryId(Long categoryId);

    List<ItemEntity> findAll();

    List<Item> getAllItemsWithAmountAndCategory();

    void registerItem(AddItemRequest addItemRequest);
}
