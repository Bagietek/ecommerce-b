package pl.akademiaspecjalistowit.ecommerce.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;
import pl.akademiaspecjalistowit.ecommerce.client.model.ClientBo;
import pl.akademiaspecjalistowit.ecommerce.item.model.ItemBo;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderBo {

    public OrderBo(UUID technicalId, ItemBo itemBo, Integer amount, ClientBo clientBo, OrderDetailsBo orderDetailsBo) {
        this.technicalId = technicalId;
        this.itemBo = itemBo;
        this.amount = amount;
        this.clientBo = clientBo;
        this.orderDetailsBo = orderDetailsBo;
    }

    private Long id;
    private UUID technicalId;
    private ItemBo itemBo;
    private Integer amount;
    private ClientBo clientBo;
    private OrderDetailsBo orderDetailsBo;

}
