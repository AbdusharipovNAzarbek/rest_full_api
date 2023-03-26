package com.company.rest_full_api.task2.controller;

import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task2.entity.Language;
import com.company.rest_full_api.task2.payload.LanguageDto;
import com.company.rest_full_api.task2.service.LanguageService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("aoi/language")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> getLanguage() {
        return languageService.getLanguage();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Integer id){
        return languageService.getLanguagesById(id);
    }
//    @PostMapping()
//    public ResponseEntity<ApiResponse> createLanguage(@Valid @RequestBody LanguageDto languageDto){
//        return languageService.createLanguage(languageDto);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id){
        return languageService.deleteLanguage(id);
    }
}
