package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.warehouse.model.WarehouseBo;
import pl.akademiaspecjalistowit.model.UpdateWarehouseStockRequest;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseDataService warehouseDataService;

    public WarehouseBo getWarehouseStock(Long warehouseId) {
        return warehouseDataService.getWarehouse(warehouseId);
    }

    public void processNewItem(ItemBo item, long amount){
        WarehouseBo warehouseBo = new WarehouseBo(
                UUID.randomUUID(),
                item,
                amount
        );
        warehouseDataService.save(warehouseBo);
    }

    @Override
    @Transactional
    public void updateWarehouseStock(UpdateWarehouseStockRequest updateWarehouseStockRequest) {
        WarehouseBo warehouseBo = warehouseDataService.getWarehouseByTechId(
                UUID.fromString(updateWarehouseStockRequest.getTechnicalId())
        );
        warehouseBo.updateNumberOfProducts(Long.valueOf(updateWarehouseStockRequest.getAmount()));
        warehouseDataService.save(warehouseBo);
    }
}
