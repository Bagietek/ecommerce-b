package pl.akademiaspecjalistowit.ecommerce.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.akademiaspecjalistowit.api.GuestApi;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemView;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;
import pl.akademiaspecjalistowit.model.Item;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
public class GuestController implements GuestApi {
    private ItemService itemService;

    @Override
    public ResponseEntity<List<Item>> getItems() {
        log.info("Returning all items");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                System.out.println("Zalogowany użytkownik posiada rolę: " + authority.getAuthority());
            }
        }

        List<Item> items = itemService.getAllItemFromView();

        return ResponseEntity
                .ok()
                .body(items);
    }
}
