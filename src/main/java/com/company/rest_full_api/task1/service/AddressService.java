package com.company.rest_full_api.task1.service;

import com.company.rest_full_api.task1.entity.Address;
import com.company.rest_full_api.task1.payload.AddressDto;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<List<Address>> getAddress() {
        return ResponseEntity.status(HttpStatus.OK).body(addressRepository.findAll());
    }

    public ResponseEntity<Address> getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            return ResponseEntity.status(200).body(optionalAddress.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<ApiResponse> createAddress(AddressDto addressDto) {
        if (addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday manzil mavjud", false));
        }
        Address address = new Address(addressDto.getStreet(), addressDto.getHomeNumber());
        addressRepository.save(address);
        return ResponseEntity.status(200).body(new ApiResponse("Manzil muvaffaqiyatli saqlandi", true));
    }

    public ResponseEntity<ApiResponse> editAddress(Integer id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Bunday manzil topilmadi", false));
        }
        if (addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday manzil mavjud", false));
        }
        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Manzil muvaffaqiyatli tahrirlandi", true));
    }

    public ResponseEntity<ApiResponse> deleteAddress(Integer id) {
        if (addressRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Bunday manzil topilmadi", false));
        }
        addressRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Manzil muvaffaqiyatli o'chirildi", true));
    }
}
