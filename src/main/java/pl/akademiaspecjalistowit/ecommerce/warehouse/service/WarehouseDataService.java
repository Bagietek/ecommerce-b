package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WarehouseDataService {

    private WarehouseRepository warehouseRepository;

    public Optional<WarehouseEntity> getWarehouse(Long id){
        return warehouseRepository.findById(id);
    }
}
