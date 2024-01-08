package pl.akademiaspecjalistowit.ecommerce.warehouse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import pl.akademiaspecjalistowit.api.WarehouseApi;
import pl.akademiaspecjalistowit.ecommerce.warehouse.service.WarehouseService;
import pl.akademiaspecjalistowit.ecommerce.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.model.UpdateWarehousePriceRequest;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/warehouse")
public class WarehouseController implements WarehouseApi {

    private WarehouseService warehouseService;

    @GetMapping("/{warehouseId}")
    public WarehouseEntity getWarehouseStock(@PathVariable Long warehouseId){
        return warehouseService.getWarehouseStock(warehouseId);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return WarehouseApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> updateWarehousePrice(UpdateWarehousePriceRequest updateWarehousePriceRequest) {
        log.info("Updating warehouse item price");
        return ResponseEntity.ok().build();
    }
}
