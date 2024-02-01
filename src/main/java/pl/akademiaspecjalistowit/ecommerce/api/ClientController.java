package pl.akademiaspecjalistowit.ecommerce.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.akademiaspecjalistowit.api.ClientApi;
import pl.akademiaspecjalistowit.api.GuestApi;
import pl.akademiaspecjalistowit.api.SellerApi;
import pl.akademiaspecjalistowit.model.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class ClientController implements ClientApi {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ClientApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> addFunds(AddFundsRequest addFundsRequest) {
        log.info("Founds update: {}", addFundsRequest.toString());
        return ResponseEntity.status(501).build();
    }

    @Override
    public ResponseEntity<Void> addItemsToCart(UpdateWarehouseStockRequest updateWarehouseStockRequest) {
        log.info("Adding items to cart");
        return ResponseEntity.status(501).build();
    }

    @Override
    public ResponseEntity<Void> deleteItemsFromCart(String technicalId) {
        log.info("Deleting items from cart");
        return ResponseEntity.status(501).build();
    }

    @Override
    public ResponseEntity<Void> registerClient(RegisterClientRequest registerClientRequest) {
        log.info("Registering new client");
        return ResponseEntity.status(501).build();
    }

    @Override
    public ResponseEntity<SubmitOrderFromCart200Response> submitOrderFromCart(SubmitOrderFromCartRequest submitOrderFromCartRequest) {
        log.info("Submitting order");
        return ResponseEntity.status(501).build();
    }
}
