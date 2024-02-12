package pl.akademiaspecjalistowit.ecommerce.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetailsEntity {

    public OrderDetailsEntity(UUID technicalId, Date orderDate, Date deliveryDate, String shippingMethod, String status) {
        this.technicalId = technicalId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.shippingMethod = shippingMethod;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "shipping_method")
    private String shippingMethod;

    private String status;
}
