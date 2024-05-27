package com.example.part1.service;

import com.example.part1.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

    List<Category> findAll();
    Category saveCategory(Category category);
    Category updateCategory(Long categoryId, Category category);
}
