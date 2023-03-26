package com.company.rest_full_api.task2.repository;

import com.company.rest_full_api.task2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
}
