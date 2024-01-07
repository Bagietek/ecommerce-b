package pl.akademiaspecjalistowit.ecommerce.item.service;

import pl.akademiaspecjalistowit.ecommerce.entity.ItemEntity;

import java.util.List;

public interface ItemService {

    public List<ItemEntity> findByCategoryId(Long categoryId);
}
