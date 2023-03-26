package com.company.rest_full_api.task2.repository;

import com.company.rest_full_api.task2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}
