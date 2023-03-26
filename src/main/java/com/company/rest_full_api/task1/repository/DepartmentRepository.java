package com.company.rest_full_api.task1.repository;

import com.company.rest_full_api.task1.entity.Company;
import com.company.rest_full_api.task1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndCompany(String name, Company company);
}
