package pl.akademiaspecjalistowit.ecommerce.category.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.mapper.CategoryMapper;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;
import pl.akademiaspecjalistowit.ecommerce.category.repository.CategoryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryDataService {
    private CategoryRepository categoryRepository;

    public Optional<CategoryEntity> getCategoryEntityByName(String name){
        return categoryRepository.findCategoryEntityByName(name);
    }

    public void save(String category){
        categoryRepository.save(new CategoryEntity(category));
    }

    public CategoryBo saveAndReturn(String category){
        CategoryEntity categoryEntity = new CategoryEntity(category);
        categoryRepository.save(categoryEntity);
        return CategoryMapper.boFromEntity(categoryEntity);
    }

    public boolean existsByName(String name){
        return categoryRepository.existsByName(name);
    }
}
