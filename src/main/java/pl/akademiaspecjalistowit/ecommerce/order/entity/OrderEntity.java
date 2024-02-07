package pl.akademiaspecjalistowit.ecommerce.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "client_order")
public class OrderEntity {

    public OrderEntity(OrderDetailsEntity orderDetailsEntity, UUID technicalId, ItemEntity itemId, Long totalAmount, ClientEntity clientId) {
        this.orderDetailsEntity = orderDetailsEntity;
        this.technicalId = technicalId;
        this.itemId = itemId;
        this.totalAmount = totalAmount;
        this.clientId = clientId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_details_id")
    private OrderDetailsEntity orderDetailsEntity;

    @Column(name = "technical_id")
    private UUID technicalId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemId;

    @Column(name = "total_amount")
    private Long totalAmount;

    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientId;
}
