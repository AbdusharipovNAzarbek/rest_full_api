package com.company.rest_full_api.task1.service;

import com.company.rest_full_api.task1.entity.Address;
import com.company.rest_full_api.task1.entity.Company;
import com.company.rest_full_api.task1.entity.Worker;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.payload.WorkerDto;
import com.company.rest_full_api.task1.repository.AddressRepository;
import com.company.rest_full_api.task1.repository.CompanyRepository;
import com.company.rest_full_api.task1.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ResponseEntity<List<Worker>> getWorker() {
        return ResponseEntity.status(200).body(workerRepository.findAll());
    }

    public ResponseEntity<Worker> getWorkerById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(optionalWorker.get());
    }

    public ResponseEntity<ApiResponse> createWorker(WorkerDto workerDto) {
        Optional<Company> optionalCompany = companyRepository.findById(workerDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday Kompaniya mavjud emas", false));
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday manzil mavjud emas", false));
        }
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Bunday telefon raqamli ishchi mavjud", false));
        }
        Worker worker = new Worker(workerDto.getName(), workerDto.getPhoneNumber(), optionalAddress.get(), optionalCompany.get());
        workerRepository.save(worker);
        return ResponseEntity.status(200).body(new ApiResponse("Ishchi muvaffaqiyatli saqlandi", true));
    }

    public ResponseEntity<ApiResponse> editWorker(WorkerDto workerDto, Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday ishchi topilmadi", false));
        }
        Optional<Company> optionalCompany = companyRepository.findById(workerDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday kompaniya mavjud emas", false));
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday Manzil mavjud emas", false));
        }
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Bunday telefon raqamli ishchi mavjud", false));
        }
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setCompany(optionalCompany.get());
        workerRepository.save(worker);
        return ResponseEntity.status(200).body(new ApiResponse("Ishchi muvaffaqiyatli taxrirlandi", true));
    }

    public ResponseEntity<ApiResponse> deleteWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday ishchi topilmadi", false));
        }
        workerRepository.deleteById(id);
        return ResponseEntity.status(200).body(new ApiResponse("Ishchi muvaffaqiyatli o'chirildi", true));
    }
}
