package pl.akademiaspecjalistowit.ecommerce.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.akademiaspecjalistowit.api.ClientApi;
import pl.akademiaspecjalistowit.api.GuestApi;
import pl.akademiaspecjalistowit.api.SellerApi;
import pl.akademiaspecjalistowit.model.AddFundsRequest;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.Item;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class ClientController implements ClientApi, SellerApi, GuestApi {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ClientApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> addFunds(AddFundsRequest addFundsRequest) {
        log.info("Founds update: {}", addFundsRequest.toString());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addItem(AddItemRequest addItemRequest) {
        log.info("Adding item: {}", addItemRequest.toString());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Item>> getItems() {
        log.info("Returning all items");
        return ResponseEntity.ok().build();
    }
}
