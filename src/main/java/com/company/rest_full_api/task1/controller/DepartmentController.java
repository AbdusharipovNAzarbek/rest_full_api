package com.company.rest_full_api.task1.controller;

import com.company.rest_full_api.task1.entity.Department;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.payload.DepartmentDto;
import com.company.rest_full_api.task1.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getDepartment() {
        return departmentService.getDepartment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDepartment(@Valid @PathVariable Integer id, @RequestBody DepartmentDto departmentDto){
        return departmentService.editDepartment(id,departmentDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        return departmentService.deleteDepartment(id);
    }

}
