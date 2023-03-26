package com.company.rest_full_api.task2.service;

import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task2.entity.Category;
import com.company.rest_full_api.task2.payload.CategoryDto;
import com.company.rest_full_api.task2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<List<Category>> getCategory() {
        return ResponseEntity.status(200).body(categoryRepository.findAll());
    }

    public ResponseEntity<Category> getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(optionalCategory.get());
    }

    public ResponseEntity<ApiResponse> createCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday nomli kategoriya mavjud", false));
        }
        Category category = new Category(categoryDto.getName(), categoryDto.getDescription());
        categoryRepository.save(category);
        return ResponseEntity.status(200).body(new ApiResponse("Kategoriya muvaffaqiyatli yaratildi", true));
    }

    public ResponseEntity<ApiResponse> editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday kategoriya topilmadi", false));
        }
        if (categoryRepository.existsByName(categoryDto.getName())) {
            return ResponseEntity.status(409).body(new ApiResponse(" Bunday nomli kategoriya mavjud", false));
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return ResponseEntity.status(200).body(new ApiResponse("Kategoriya muvaffaqiyatli taxrirlandi", true));
    }

    public ResponseEntity<ApiResponse> deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday kategoriya topilmadi", false));
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.status(202).body(new ApiResponse("Katgeoriya muvaffaqiyatli o'chiirldi", true));
    }
}
