package com.company.rest_full_api.task1.repository;

import com.company.rest_full_api.task1.entity.Address;
import com.company.rest_full_api.task1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpNameAndAddress(String corpName, Address address);
}
