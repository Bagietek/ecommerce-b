package pl.akademiaspecjalistowit.ecommerce.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryBo {
    public CategoryBo(String name) {
        this.name = name;
    }

    private Long id;
    private String name;
}
