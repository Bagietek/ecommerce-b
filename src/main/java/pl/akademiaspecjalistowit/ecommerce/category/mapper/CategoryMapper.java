package pl.akademiaspecjalistowit.ecommerce.category.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;

public class CategoryMapper {
    public static CategoryEntity fromString(String category){
        return new CategoryEntity(category);
    }
}
