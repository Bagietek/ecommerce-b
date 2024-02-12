package pl.akademiaspecjalistowit.ecommerce.item;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;
import pl.akademiaspecjalistowit.model.Item;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/all/view")
    public List<Item> findAllFromView(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                System.out.println("Zalogowany użytkownik posiada rolę: " + authority.getAuthority());
            }
        }
        return itemService.getAllItemFromView();
    }
}
