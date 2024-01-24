package pl.akademiaspecjalistowit.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "client_order")
public class ClientOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    @Column(name = "item_id")
    private Set<ItemEntity> itemId;

    @Column(name = "total_amount")
    private Long totalAmount;

    private String currency;

    @Column(name = "order_date_time")
    private Date orderDateTime;

    @Column(name = "delivery_date_time")
    private Date deliveryDateTime;

    @Column(name = "shipping_method")
    private String shippingMethod;

    private String status;

    @OneToOne
    @JoinColumn(name = "id")
    //@Column(name = "client_id")
    private ClientEntity clientId;
}
