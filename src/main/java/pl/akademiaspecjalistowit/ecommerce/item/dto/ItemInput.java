package pl.akademiaspecjalistowit.ecommerce.item.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ItemInput {
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private int amount;
}
