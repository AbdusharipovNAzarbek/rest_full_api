package com.company.rest_full_api.task2.repository;

import com.company.rest_full_api.task2.controller.LanguageController;
import com.company.rest_full_api.task2.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);
}
