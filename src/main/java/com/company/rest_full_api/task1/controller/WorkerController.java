package com.company.rest_full_api.task1.controller;

import com.company.rest_full_api.task1.entity.Worker;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.payload.WorkerDto;
import com.company.rest_full_api.task1.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> getWorker() {
        return workerService.getWorker();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Integer id) {
        return workerService.getWorkerById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createWorker(@Valid @RequestBody WorkerDto workerDto){
        return workerService.createWorker(workerDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@Valid @RequestBody WorkerDto workerDto, @PathVariable Integer id){
        return workerService.editWorker(workerDto,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        return workerService.deleteWorker(id);
    }





}
