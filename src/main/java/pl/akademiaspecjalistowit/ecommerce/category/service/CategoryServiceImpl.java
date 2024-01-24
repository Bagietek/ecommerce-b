package pl.akademiaspecjalistowit.ecommerce.category.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.exception.CategoryNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.category.mapper.CategoryMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private CategoryDataService categoryDataService;
    public CategoryEntity processStringInput(String category){
        if(!categoryDataService.existsByName(category)){
            return categoryDataService.saveAndReturn(category);
        }
        return categoryDataService.getCategoryEntityByName(category)
                .orElseThrow(CategoryNotFoundException::new);
    }

}
