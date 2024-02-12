package pl.akademiaspecjalistowit.ecommerce.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CartBo {

    public CartBo(UUID technicalId, ItemBo itemsBo, Long numberOfProducts, ClientBo clientBo, String promoCode) {
        this.technicalId = technicalId;
        this.itemsBo = itemsBo;
        this.numberOfProducts = numberOfProducts;
        this.clientBo = clientBo;
        this.promoCode = promoCode;
    }

    private Long id;
    private UUID technicalId;
    private ItemBo itemsBo;
    private Long numberOfProducts;
    private ClientBo clientBo;
    private String promoCode;
}
