package pl.akademiaspecjalistowit.ecommerce.warehouse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.ecommerce.entity.WarehouseEntity;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/warehouse")
public class WarehouseController {

    private WarehouseService warehouseService;

    @GetMapping("/{warehouseId}")
    public WarehouseEntity getWarehouseStock(@PathVariable Long warehouseId){
        return warehouseService.getWarehouseStock(warehouseId);
    }
}
