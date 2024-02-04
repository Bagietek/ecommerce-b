package pl.akademiaspecjalistowit.ecommerce.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.akademiaspecjalistowit.api.SellerApi;
import pl.akademiaspecjalistowit.ecommerce.item.service.ItemService;
import pl.akademiaspecjalistowit.ecommerce.seller.service.SellerService;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.AddSellerRequest;
import pl.akademiaspecjalistowit.model.UpdateItemPriceRequest;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class SellerController implements SellerApi {
    private ItemService itemService;
    private SellerService sellerService;

    @Override
    public ResponseEntity<Void> addItem(AddItemRequest addItemRequest) {
        itemService.registerItem(addItemRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateItemPrice(UpdateItemPriceRequest updateItemPriceRequest) {
        itemService.updateItemPrice(updateItemPriceRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addSeller(AddSellerRequest addSellerRequest) {
        sellerService.addSeller(addSellerRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteItem(String itemTechnicalId, String sellerTechnicalId) {
        itemService.deleteItem(itemTechnicalId, sellerTechnicalId);
        return ResponseEntity.ok().build();
    }

}
