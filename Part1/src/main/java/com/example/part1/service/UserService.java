package com.example.part1.service;

import com.example.part1.dto.*;
import com.example.part1.exceptions.ApiExceptionResponse;
import com.example.part1.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    SuccessfulLoginDTO login(AuthDTO dto) throws ApiExceptionResponse;
    User register(User user) throws ApiExceptionResponse;
    User findUserById(Long userId);

}
