package com.company.rest_full_api.task2.service;

import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task2.entity.Language;
import com.company.rest_full_api.task2.payload.LanguageDto;
import com.company.rest_full_api.task2.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public ResponseEntity<List<Language>> getLanguage() {
        return ResponseEntity.status(200).body(languageRepository.findAll());
    }

    public ResponseEntity<Language> getLanguagesById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(optionalLanguage.get());
    }

    public ResponseEntity<ApiResponse> deleteLanguage(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday dasturlash tili topilmadi", false));
        }
        languageRepository.deleteById(id);
        return ResponseEntity.status(202).body(new ApiResponse("Dasturlash tili mavaffaqiyatli o'chirildi", true));
    }

//    public ResponseEntity<ApiResponse> createLanguage(LanguageDto languageDto) {
//        if (languageRepository.existsByName(languageDto.getName())) {
//            return ResponseEntity.status(409).body(new ApiResponse("Bunday dasturlash tili mavjud",false));
//        }
//    }

}
