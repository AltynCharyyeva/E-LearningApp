/*
package com.example.a2.controller;

import com.example.a2.dto.AuthDTO;
import com.example.a2.dto.UserCreationDTO;
import com.example.a2.exceptions.ApiExceptionResponse;
import com.example.a2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000/")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO auth) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(auth));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserCreationDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(user));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
*/
