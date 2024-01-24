package pl.akademiaspecjalistowit.ecommerce.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    public ItemDto(String name, String description, BigDecimal price, String categoryName, long numberOfProducts) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = categoryName;
        this.number_of_products = numberOfProducts;
    }

    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private long number_of_products;
}
