package com.company.rest_full_api.task2.controller;

import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task2.entity.Category;
import com.company.rest_full_api.task2.payload.CategoryDto;
import com.company.rest_full_api.task2.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getCategory(){
        return categoryService.getCategory();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }
    @PostMapping()
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@Valid @PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id,categoryDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }

}
