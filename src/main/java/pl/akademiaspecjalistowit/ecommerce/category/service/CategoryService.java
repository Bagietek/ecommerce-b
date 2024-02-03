package pl.akademiaspecjalistowit.ecommerce.category.service;

import pl.akademiaspecjalistowit.ecommerce.category.entity.CategoryEntity;
import pl.akademiaspecjalistowit.ecommerce.category.model.CategoryBo;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    CategoryBo processStringInput(String category);
}
