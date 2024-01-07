package pl.akademiaspecjalistowit.ecommerce.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.akademiaspecjalistowit.api.SellerApi;
import pl.akademiaspecjalistowit.model.AddItemRequest;
import pl.akademiaspecjalistowit.model.RegisterSellerRequest;

import java.util.Optional;

@RestController
@Slf4j
public class SellerController implements SellerApi {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return SellerApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> addItem(AddItemRequest addItemRequest) {
        log.info("Adding item");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> registerSeller(RegisterSellerRequest registerSellerRequest) {
        log.info("Registering new seller");
        return ResponseEntity.ok().build();
    }
}
