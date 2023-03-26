package com.company.rest_full_api.task1.service;

import com.company.rest_full_api.task1.entity.Address;
import com.company.rest_full_api.task1.entity.Company;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.payload.CompanyDto;
import com.company.rest_full_api.task1.repository.AddressRepository;
import com.company.rest_full_api.task1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<List<Company>> getCompany() {
        return ResponseEntity.status(200).body(companyRepository.findAll());
    }

    public ResponseEntity<Company> getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(200).body(optionalCompany.get());
    }

    public ResponseEntity<ApiResponse> createCompany(CompanyDto companyDto) {
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday manzil topilmadi", false));
        }
        if (companyRepository.existsByCorpNameAndAddress(companyDto.getCorpName(), optionalAddress.get())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday Kompaniya mavjud", false));
        }
        Company company = new Company(companyDto.getCorpName(), companyDto.getDirectorName(), optionalAddress.get());
        companyRepository.save(company);
        return ResponseEntity.status(200).body(new ApiResponse("Kompaniya muvaffaqiyatli saqlandi", true));
    }

    public ResponseEntity<ApiResponse> editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday kompaniya topilmadi", false));
        }
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday manzil topilmadi", false));
        }
        if (companyRepository.existsByCorpNameAndAddress(companyDto.getCorpName(), optionalAddress.get())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday Kompaniya mavjud", false));
        }
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(company.getDirectorName());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Kompaniya muvaffaqiyatli saqlandi", true));
    }

    public ResponseEntity<ApiResponse> deleteCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday kompaniya topilmadi", false));
        }
        companyRepository.delete(optionalCompany.get());
        return ResponseEntity.status(200).body(new ApiResponse("Kompaniya muvaffaqiyatli o'chirildi", true));
    }
}
