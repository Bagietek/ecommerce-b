package pl.akademiaspecjalistowit.ecommerce.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.seller.model.SellerBo;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseInputDataException;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class WarehouseBo {

    public WarehouseBo(UUID technicalId, ItemBo itemBo, Long numberOfProducts, SellerBo sellerBo) {
        this.technicalId = technicalId;
        this.itemBo = itemBo;
        this.numberOfProducts = numberOfProducts;
        this.sellerBo = sellerBo;
    }

    private Long id;
    private UUID technicalId;
    private ItemBo itemBo;
    private Long numberOfProducts;
    private SellerBo sellerBo;
    public void updateNumberOfProducts(Long numberOfProducts){
        if(numberOfProducts <= 0){
            throw new WarehouseInputDataException();
        }
        this.numberOfProducts = numberOfProducts;
    }
}
