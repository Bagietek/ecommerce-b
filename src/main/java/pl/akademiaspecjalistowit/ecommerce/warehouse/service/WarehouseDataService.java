package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WarehouseDataService {

    private WarehouseRepository warehouseRepository;

    public Optional<WarehouseEntity> getWarehouse(Long id){
        return warehouseRepository.findById(id);
    }

    public Optional<WarehouseEntity> existsByItemId(ItemEntity item){
        return warehouseRepository.existsByItemId(item);
    }

    public void save(WarehouseEntity warehouse){
        warehouseRepository.save(warehouse);
    }
}
