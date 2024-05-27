package com.example.part2.service;

import com.example.part2.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

    List<Category> findAll();
    Category saveCategory(Category category);
    Category updateCategory(Long categoryId, Category category);
}
