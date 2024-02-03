package pl.akademiaspecjalistowit.ecommerce.category.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;

public class CategoryMapper {
    public static CategoryEntity entityFromString(String category){
        return new CategoryEntity(category);
    }

    public static CategoryBo boFromEntity(CategoryEntity categoryEntity){
        if(categoryEntity == null){
            return null;
        }

        return new CategoryBo(
                categoryEntity.getId(),
                categoryEntity.getName()
        );
    }

    public static CategoryEntity entityFromBo(CategoryBo categoryBo){
        if(categoryBo == null){
            return null;
        }
        return new CategoryEntity(
                categoryBo.getId(),
                categoryBo.getName()
        );
    }
}
