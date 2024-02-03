package pl.akademiaspecjalistowit.ecommerce.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.warehouse.mapper.WarehouseMapper;
import pl.akademiaspecjalistowit.ecommerce.warehouse.model.WarehouseBo;
import pl.akademiaspecjalistowit.ecommerce.warehouse.repository.WarehouseRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WarehouseDataService {

    private WarehouseRepository warehouseRepository;

    public WarehouseBo getWarehouse(Long id){
        return WarehouseMapper.boFromEntity(
                warehouseRepository.findById(id).orElseThrow(WarehouseNotFoundException::new)
        );
    }

    public WarehouseBo getWarehouseByTechId(UUID technicalId){
        return WarehouseMapper.boFromEntity(
                warehouseRepository.findByTechnicalId(technicalId).orElseThrow(WarehouseNotFoundException::new)
                );
    }

    public void save(WarehouseBo warehouse){
        warehouseRepository.save(WarehouseMapper.entityFromBo(warehouse));
    }
}
