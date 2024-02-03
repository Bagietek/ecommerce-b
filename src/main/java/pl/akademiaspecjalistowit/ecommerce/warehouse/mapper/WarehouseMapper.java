package pl.akademiaspecjalistowit.ecommerce.warehouse.mapper;

import pl.akademiaspecjalistowit.ecommerce.item.mapper.ItemMapper;
import pl.akademiaspecjalistowit.ecommerce.warehouse.entity.WarehouseEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.model.WarehouseBo;

public class WarehouseMapper {

    public static WarehouseEntity entityFromBo(WarehouseBo warehouseBo){
        if(warehouseBo == null){
            return null;
        }
        return new WarehouseEntity(
                warehouseBo.getId(),
                warehouseBo.getTechnicalId(),
                ItemMapper.entityFromBo(warehouseBo.getItemBo()),
                warehouseBo.getNumberOfProducts()
        );
    }

    public static WarehouseBo boFromEntity(WarehouseEntity warehouse){
        if(warehouse == null){
            return null;
        }
        return new WarehouseBo(
                warehouse.getId(),
                warehouse.getTechnicalId(),
                ItemMapper.boFromEntity(warehouse.getItemId()),
                warehouse.getNumberOfProducts()
        );
    }
}
