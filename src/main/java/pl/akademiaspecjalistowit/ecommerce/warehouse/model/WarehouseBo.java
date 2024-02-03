package pl.akademiaspecjalistowit.ecommerce.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseInputDataException;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class WarehouseBo {

    public WarehouseBo(UUID technicalId, ItemBo itemBo, Long numberOfProducts) {
        this.technicalId = technicalId;
        this.itemBo = itemBo;
        this.numberOfProducts = numberOfProducts;
    }

    private Long id;
    private UUID technicalId;
    private ItemBo itemBo;
    private Long numberOfProducts;

    public void updateNumberOfProducts(Long numberOfProducts){
        if(numberOfProducts <= 0){
            throw new WarehouseInputDataException();
        }
        this.numberOfProducts = numberOfProducts;
    }
}
