package pl.akademiaspecjalistowit.ecommerce.item.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "item_summary")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemView {

    public ItemView(String name, String description, BigDecimal price, String categoryName, Integer numberOfProducts) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
        this.numberOfProducts = numberOfProducts;
    }

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "number_of_products")
    private Integer numberOfProducts;
}
