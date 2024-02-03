package pl.akademiaspecjalistowit.ecommerce.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;
import pl.akademiaspecjalistowit.ecommerce.item.exception.ItemInvalidDataException;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor
@Getter
public class ItemBo {

    public ItemBo(UUID technicalId, String description, CategoryBo categoryBo, String name, ItemAvailability itemAvailability, BigDecimal price) {
        this.technicalId = technicalId;
        this.description = description;
        this.categoryBo = categoryBo;
        this.name = name;
        this.itemAvailability = itemAvailability;
        this.price = price;
    }

    private Long id;
    private UUID technicalId;
    private String description;
    private CategoryBo categoryBo;
    private String name;
    @Setter
    private ItemAvailability itemAvailability;
    private BigDecimal price;

    public void updatePrice(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) <= 0){
            throw new ItemInvalidDataException();
        }
        this.price = price;
    }
}
