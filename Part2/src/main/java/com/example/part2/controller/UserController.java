package com.example.part2.controller;


import com.example.part2.dto.AuthDTO;
import com.example.part2.dto.UserCreationDTO;
import com.example.part2.exceptions.ApiExceptionResponse;
import com.example.part2.model.User;
import com.example.part2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.part2.service.TokenGenerator.generateToken;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000/")
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO auth) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(auth));
    }

    @PostMapping("/loginUser")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());

        if (loggedInUser != null) {
            String token = generateToken();
            loggedInUser.setToken(token);
            userService.saveUser(loggedInUser); // Update user with token

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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


    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/export/{userId}")
    public ResponseEntity exportUserDetails(@PathVariable Long userId, @RequestParam String fileType) {
        // Generate XML content
        String xmlContent = userService.exportUserDetails(userId, fileType);

        // Write XML content to a file
        String fileName = "user_details_" + userId + ".xml";
        String filePath = "D:\\Documents\\Anul III\\Sem 2\\PS\\ELearningApp\\project-AltynCharyyeva\\a2\\src\\main\\java\\com\\example\\a2\\exportedFiles\\" + fileName; // Specify the directory path where you want to save the file
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(xmlContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving XML file.");
        }

        return ResponseEntity.ok("XML file exported successfully: " + fileName);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authorization.substring(7); // Extract token after "Bearer "
        User user = userService.getUserByToken(token);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authorization.substring(7); // Extract token after "Bearer "
        User user = userService.getUserByToken(token);

        if (user != null) {
            userService.logout(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




}
