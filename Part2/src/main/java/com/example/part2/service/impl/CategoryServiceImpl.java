package com.example.part2.service.impl;

import com.example.part2.model.Category;
import com.example.part2.repository.CategoryRepository;
import com.example.part2.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll(){
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, Category category){
        boolean exists = categoryRepository.findById(categoryId).isPresent();
        if(exists){
            Category originalCategory = categoryRepository.findById(categoryId).get();
            if(category.getCategoryName() != null){
                originalCategory.setCategoryName(category.getCategoryName());
            }
            return categoryRepository.save(originalCategory);
        }
        return null;
    }
}
