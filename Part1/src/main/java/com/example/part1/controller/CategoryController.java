package com.example.part1.controller;

import com.example.part1.model.Category;
import com.example.part1.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin("http://localhost:3000/")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity findAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity createCategory(@RequestBody Category category){
        Category createdCategory = categoryService.saveCategory(category);
        if(createdCategory != null){
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/update/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable Long categoryId, @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(categoryId, updatedCategory);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
