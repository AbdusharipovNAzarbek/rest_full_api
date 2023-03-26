package com.company.rest_full_api.task2.service;

import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task2.entity.User;
import com.company.rest_full_api.task2.payload.UserDto;
import com.company.rest_full_api.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.status(200).body(userRepository.findAll());
    }

    public ResponseEntity<User> getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(optionalUser.get());
    }

    public ResponseEntity<ApiResponse> createUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday elektron pochtali foydalanuvchi mavjud", false));
        }
        User user = new User(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.status(202).body(new ApiResponse("Foydalanuvchi muvaffaqiyatli saqlandi", true));
    }

    public ResponseEntity<ApiResponse> editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Foydalanuvchi topilmadi", false));
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(409).body(new ApiResponse("Bunday elektron pochtali foydalanuvchi mavjud", false));
        }
        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return ResponseEntity.status(202).body(new ApiResponse("Foydalanuvchi muvaffaqiyatli taxrirlandi", true));
    }

    public ResponseEntity<ApiResponse> deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("Bunday foydalanuvchi topilmadi", false));
        }
        userRepository.delete(optionalUser.get());
        return ResponseEntity.status(202).body(new ApiResponse("Foydalanuvchi muvaffaqiyatli o'chirildi", true));
    }
}
