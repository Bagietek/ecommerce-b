package pl.akademiaspecjalistowit.ecommerce.cart.service;

import pl.akademiaspecjalistowit.ecommerce.cart.model.CartBo;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.model.AddItemsToCartRequest;
import pl.akademiaspecjalistowit.model.GetAllCartItems200Response;
import pl.akademiaspecjalistowit.model.UpdateWarehouseStockRequest;

import java.util.List;
import java.util.UUID;

public interface CartService {
    void addItem(AddItemsToCartRequest addItemsToCartRequest);

    void deleteItem(UUID itemTechnicalId);

    void deleteClientCart(UUID clientTechnicalId);

    GetAllCartItems200Response getClientCart(String technicalId);

    List<CartBo> getClientCartBo(ClientBo clientBo);
}
