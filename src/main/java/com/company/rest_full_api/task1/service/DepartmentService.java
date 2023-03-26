package com.company.rest_full_api.task1.service;

import com.company.rest_full_api.task1.entity.Company;
import com.company.rest_full_api.task1.entity.Department;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.payload.DepartmentDto;
import com.company.rest_full_api.task1.repository.CompanyRepository;
import com.company.rest_full_api.task1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ResponseEntity<List<Department>> getDepartment() {
        return ResponseEntity.status(200).body(departmentRepository.findAll());
    }

    public ResponseEntity<Department> getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(optionalDepartment.get());
    }

    public ResponseEntity<ApiResponse> createDepartment(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday kompaniya topilmadi", false));
        }
        if (departmentRepository.existsByNameAndCompany(departmentDto.getName(), optionalCompany.get())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday bo'lim mavjud", false));
        }
        Department department = new Department(departmentDto.getName(), optionalCompany.get());
        departmentRepository.save(department);
        return ResponseEntity.status(200).body(new ApiResponse("Bo'lim muvaffaqiyatli yaratildi", true));
    }

    public ResponseEntity<ApiResponse> editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday bo'lim topilmadi", false));
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday kompamiya topilmadi", false));
        }
        if (departmentRepository.existsByNameAndCompany(departmentDto.getName(), optionalCompany.get())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday bo'lim mavjud", false));
        }
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return ResponseEntity.status(200).body(new ApiResponse("Bo'lim muvaffaqiyatli tahrirlandi", true));
    }

    public ResponseEntity<ApiResponse> deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday bo'lim topilmadi", false));
        }
        departmentRepository.deleteById(id);
        return ResponseEntity.status(202).body(new ApiResponse("Bo'lim muvaffaqiyatli o'chirildi", true));
    }
}
