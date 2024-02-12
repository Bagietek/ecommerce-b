package pl.akademiaspecjalistowit.ecommerce.warehouse.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.item.entity.ItemEntity;
import pl.akademiaspecjalistowit.ecommerce.seller.entity.SellerEntity;
import pl.akademiaspecjalistowit.ecommerce.warehouse.exception.WarehouseInputDataException;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "warehouse")
public class WarehouseEntity {

    public WarehouseEntity(UUID technicalId, ItemEntity item, Long amount, SellerEntity sellerEntity){
        this.itemId = item;
        this.numberOfProducts = amount;
        this.technicalId = technicalId;
        this.sellerEntity = sellerEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "item_id")
    private ItemEntity itemId;

    @Column(name = "number_of_products")
    private Long numberOfProducts;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private SellerEntity sellerEntity;

}
