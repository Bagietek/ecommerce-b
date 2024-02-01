package pl.akademiaspecjalistowit.ecommerce.item.mapper;

import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.item.dto.ItemDto;
import pl.akademiaspecjalistowit.ecommerce.item.dto.ItemInput;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ItemMapper {

    public static List<Item> fromItemDto(List<ItemDto> itemDto){
        List<Item> items = new ArrayList<>();
        for(ItemDto itemDtos : itemDto){
            Item currentItem = new Item();
            currentItem.setName(itemDtos.getName());
            currentItem.setAmount((Math.toIntExact(itemDtos.getNumber_of_products())));
            currentItem.setPrice(itemDtos.getPrice());
            currentItem.setDescription(itemDtos.getDescription());
            currentItem.setCategories(itemDtos.getCategory());
            items.add(currentItem);
        }
        return items;
    }

    public static ItemInput fromGeneratedItemRequest(AddItemRequest item){
        return new ItemInput(
                item.getItem().getName(),
                item.getItem().getDescription(),
                item.getItem().getCategories(),
                item.getItem().getPrice(),
                item.getItem().getAmount()
        );
    }
}
