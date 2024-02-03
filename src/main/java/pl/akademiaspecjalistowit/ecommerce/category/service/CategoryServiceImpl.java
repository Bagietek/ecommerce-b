package pl.akademiaspecjalistowit.ecommerce.category.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.exception.CategoryNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.category.mapper.CategoryMapper;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private CategoryDataService categoryDataService;

    // todo: Entity manager does it
    public CategoryBo processStringInput(String category){
        if(!categoryDataService.existsByName(category)){
            return categoryDataService.saveAndReturn(category);
        }
        CategoryEntity categoryEntity = categoryDataService.getCategoryEntityByName(category)
                .orElseThrow(CategoryNotFoundException::new);
        return CategoryMapper.boFromEntity(categoryEntity);
    }

}
