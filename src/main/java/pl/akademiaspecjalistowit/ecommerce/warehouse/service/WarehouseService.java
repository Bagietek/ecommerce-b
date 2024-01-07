package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.exception.WarehouseNotFoundException;

@Service
@AllArgsConstructor
public class WarehouseService{

    private final WarehouseDataService warehouseDataService;

    public WarehouseEntity getWarehouseStock(Long warehouseId) {
        return warehouseDataService.getWarehouse(warehouseId)
                .orElseThrow(WarehouseNotFoundException::new);
    }
}
