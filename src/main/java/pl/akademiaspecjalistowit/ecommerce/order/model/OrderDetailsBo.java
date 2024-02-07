package pl.akademiaspecjalistowit.ecommerce.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderDetailsBo {

    public OrderDetailsBo(UUID technicalId, Date orderDate, Date deliveryDate, ShippingMethod shippingMethod, OrderState orderState) {
        this.technicalId = technicalId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.shippingMethod = shippingMethod;
        this.orderState = orderState;
    }

    private Long id;
    private UUID technicalId;
    private Date orderDate;
    private Date deliveryDate;
    private ShippingMethod shippingMethod;
    private OrderState orderState;
}
