package pl.akademiaspecjalistowit.ecommerce.item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.ecommerce.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/all/category/{categoryId}")
    public List<ItemEntity> findAllByCategory(@PathVariable Long categoryId){
        return itemService.findByCategoryId(categoryId);
    }
}
