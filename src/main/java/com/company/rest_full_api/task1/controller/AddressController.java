package com.company.rest_full_api.task1.controller;

import com.company.rest_full_api.task1.entity.Address;
import com.company.rest_full_api.task1.payload.AddressDto;
import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task1.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAddress() {
        return addressService.getAddress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAddress(@Valid @RequestBody AddressDto addressDto) {
        return addressService.createAddress(addressDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAddress(@Valid @PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return addressService.editAddress(id, addressDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id) {
        return addressService.deleteAddress(id);
    }

}
