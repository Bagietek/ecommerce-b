package pl.akademiaspecjalistowit.ecommerce.cart.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.client.entity.ClientEntity;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "cart")
public class CartEntity {

    public CartEntity(UUID technicalId, ItemEntity itemId, Long numberOfProducts, ClientEntity clientId, String promoCode) {
        this.technicalId = technicalId;
        this.items = itemId;
        this.numberOfProducts = numberOfProducts;
        this.clientId = clientId;
        this.promoCode = promoCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity items;

    @Column(name = "number_of_products")
    private Long numberOfProducts;

    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientId;

    @Column(name = "promo_code")
    private String promoCode;
}
