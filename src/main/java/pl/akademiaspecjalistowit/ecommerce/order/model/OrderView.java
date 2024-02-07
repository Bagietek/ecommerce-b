package pl.akademiaspecjalistowit.ecommerce.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.currency.model.AccountCurrency;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "order_summary")
public class OrderView {

    public OrderView(UUID technicalId, Date orderDate, Date deliveryDate, Integer amount, String shippingMethod, String orderState) {
        this.technicalId = technicalId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amount = amount;
        this.shippingMethod = shippingMethod;
        this.orderState = orderState;
    }

    // never to use
    @Id
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    private Integer amount;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "status")
    private String orderState;
}
