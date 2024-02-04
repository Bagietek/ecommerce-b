package pl.akademiaspecjalistowit.ecommerce.item.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.seller.entity.SellerEntity;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "item")
public class ItemEntity {

    public ItemEntity(UUID technicalId, String description, CategoryEntity categoryId, String name, String availability, BigDecimal price) {
        this.technicalId = technicalId;
        this.description = description;
        this.categoryId = categoryId;
        this.name = name;
        this.availability = availability;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    private String description;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryId;

    private String name;

    private String availability;

    @Column(name = "price")
    private BigDecimal price;
}
