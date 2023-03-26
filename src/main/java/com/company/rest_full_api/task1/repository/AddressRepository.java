package com.company.rest_full_api.task1.repository;

import com.company.rest_full_api.task1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AddressRepository extends JpaRepository<Address,Integer> {
    boolean existsByStreetAndHomeNumber(String street, String homeNumber);
}
