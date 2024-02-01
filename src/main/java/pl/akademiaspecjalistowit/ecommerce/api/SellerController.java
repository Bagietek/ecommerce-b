package pl.akademiaspecjalistowit.ecommerce.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.akademiaspecjalistowit.api.SellerApi;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;
import pl.akademiaspecjalistowit.model.AddItemRequest;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class SellerController implements SellerApi {
    private ItemService itemService;

    @Override
    public ResponseEntity<Void> addItem(AddItemRequest addItemRequest) {
        log.info("Adding item");
        itemService.registerItem(addItemRequest);
        return ResponseEntity.ok().build();
    }


}
