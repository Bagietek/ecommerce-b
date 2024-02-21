package pl.akademiaspecjalistowit.ecommerce.cart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "cart_summary")
public class CartView {

    public CartView(String name, String description, BigDecimal price, String categoryName, Integer amount, String code) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
        this.amount = amount;
        this.code = code;
    }

    @Id
    @Column(name = "client")
    private UUID clientId;

    private String name;

    private String description;

    private BigDecimal price;

    @Column(name = "category_name")
    private String categoryName;

    private Integer amount;

    private String code;
}
