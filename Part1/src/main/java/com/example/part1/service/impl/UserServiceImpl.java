package com.example.part1.service.impl;
import com.example.part1.dto.*;
import com.example.part1.exceptions.ApiExceptionResponse;
import com.example.part1.model.User;
import com.example.part1.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import com.example.part1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public SuccessfulLoginDTO login(AuthDTO dto) throws ApiExceptionResponse {
        User user = userRepository.findFirstByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Username " + dto.getUsername() + " might not exist");
            errors.add("Username and password might not match");

            throw ApiExceptionResponse.builder()
                    .errors(errors)
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return SuccessfulLoginDTO.builder().role("USER").id(user.getId()).build();
    }
    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public User register(User user) throws ApiExceptionResponse {
        // Check if the username is already taken
        if (userRepository.existsUserByUsername(user.getUsername())) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Username " + user.getUsername() + " or  is already taken");

            throw ApiExceptionResponse.builder()
                    .errors(errors)
                    .message("Username already exists")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        // Create a new User object
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        // Set any other properties as needed

        // Save the new user
        User savedUser = userRepository.save(newUser);

        // Return SuccessfulRegisterDTO
        return savedUser;
    }

}
