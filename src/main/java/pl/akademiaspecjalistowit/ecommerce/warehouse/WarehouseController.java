package pl.akademiaspecjalistowit.ecommerce.warehouse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspecjalistowit.api.WarehouseApi;
import pl.akademiaspecjalistowit.ecommerce.warehouse.model.WarehouseBo;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.model.UpdateWarehouseStockRequest;

@RestController
@AllArgsConstructor
@Slf4j
public class WarehouseController implements WarehouseApi {

    private WarehouseService warehouseService;

    @GetMapping("/warehouse/{warehouseId}")
    public WarehouseBo getWarehouseStock(@PathVariable Long warehouseId){
        return warehouseService.getWarehouseStock(warehouseId);
    }

    @Override
    public ResponseEntity<Void> updateWarehouseStock(UpdateWarehouseStockRequest updateWarehouseStockRequest) {
        warehouseService.updateWarehouseStock(updateWarehouseStockRequest);
        return ResponseEntity.ok().build();
    }


}
