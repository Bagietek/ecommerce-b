package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.model.WarehouseBo;
import pl.akademiaspecjalistowit.model.UpdateWarehouseStockRequest;

public interface WarehouseService {

    WarehouseBo getWarehouseStock(Long warehouseId);

    void processNewItem(ItemBo item, Long amount, String technicalId);

    void updateWarehouseStock(UpdateWarehouseStockRequest updateWarehouseStockRequest);

    void deleteByItem(ItemBo itemBo, String sellerTechnicalId);
}
