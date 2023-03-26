package com.company.rest_full_api.task1.controller;

import com.company.rest_full_api.task1.entity.Company;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.payload.CompanyDto;
import com.company.rest_full_api.task1.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompany() {
        return companyService.getCompany();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCompany(@Valid @PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        return companyService.editCompany(id, companyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id) {
        return companyService.deleteCompany(id);
    }
}
