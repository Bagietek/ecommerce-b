package pl.akademiaspecjalistowit.ecommerce.item.mapper;

import pl.akademiaspecjalistowit.ecommerce.category.mapper.CategoryMapper;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemAvailability;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.model.Item;

public class ItemMapper {

    public static ItemBo boFromEntity(ItemEntity item){
        if (item == null){
            return null;
        }

        return new ItemBo(
                item.getId(),
                item.getTechnicalId(),
                item.getDescription(),
                CategoryMapper.boFromEntity(item.getCategoryId()),
                item.getName(),
                ItemAvailability.valueOf(item.getAvailability()),
                item.getPrice()
        );
    }

    public static ItemEntity entityFromBo(ItemBo itemBo){
        if (itemBo == null){
            return null;
        }
        return new ItemEntity(
                itemBo.getId(),
                itemBo.getTechnicalId(),
                itemBo.getDescription(),
                CategoryMapper.entityFromBo(itemBo.getCategoryBo()),
                itemBo.getName(),
                itemBo.getItemAvailability().toString(),
                itemBo.getPrice()
        );
    }

    public static Item dtoFromBo(ItemBo itemBo){
        if (itemBo == null){
            return null;
        }
        Item item = new Item();
        item.setDescription(itemBo.getDescription());
        item.setPrice(itemBo.getPrice());
        item.setAmount(item.getAmount());
        item.setName(itemBo.getName());
        item.setCategory(itemBo.getCategoryBo().getName());
        return item;
    }

    public static Item itemFromItemView(ItemView itemView){
        if (itemView == null){
            return null;
        }
        Item item = new Item();
        item.setName(itemView.getName());
        item.setAmount(item.getAmount());
        item.setPrice(itemView.getPrice());
        item.setDescription(itemView.getDescription());
        item.setCategory(itemView.getCategoryName());
        return item;
    }
}
