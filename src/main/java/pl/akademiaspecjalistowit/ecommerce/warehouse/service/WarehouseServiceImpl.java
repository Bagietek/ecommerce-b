package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseNotFoundException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseDataService warehouseDataService;

    public WarehouseEntity getWarehouseStock(Long warehouseId) {
        return warehouseDataService.getWarehouse(warehouseId)
                .orElseThrow(WarehouseNotFoundException::new);
    }

    public void processNewItem(ItemEntity item, long amount){
        WarehouseEntity warehouseEntity = new WarehouseEntity(
                UUID.randomUUID(),
                item,
                amount
        );
        warehouseDataService.save(warehouseEntity);
    }
}
