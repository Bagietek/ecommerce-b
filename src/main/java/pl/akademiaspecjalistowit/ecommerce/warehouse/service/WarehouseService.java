package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;

public interface WarehouseService {

    WarehouseEntity getWarehouseStock(Long warehouseId);

    public void processNewItem(ItemEntity item, long amount);
}
