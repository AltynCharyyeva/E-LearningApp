package com.example.part1.controller;

import com.example.part1.exceptions.ApiExceptionResponse;
import com.example.part1.model.User;
import com.example.part1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000/")
public class AuthController {
//    private UserDetailsServiceImpl userDetailsServiceImpl;
      private UserService userService;
//    private PasswordEncoder passwordEncoder;
//
//    public void setUserDetailsServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl){
//        this.userDetailsServiceImpl = userDetailsServiceImpl;
//    }
//
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
//        this.passwordEncoder = passwordEncoder;
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User createdUser = userService.register(user);
            if(createdUser != null){
                return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApiExceptionResponse e) {
            // Handle ApiExceptionResponse
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        } catch (Exception e) {
            // Log the exception details
            System.out.println("An error occurred while registering the user");
            // Return internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred. Please try again later.");
        }
    }





//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody User newUser) throws ApiExceptionResponse {
//
//        String password = newUser.getPassword();
//        String encodedPassword = passwordEncoder.encode(password);
//        String username = newUser.getUsername();
//    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) throws ApiExceptionResponse{
//        if(userDetailsService.)
//    }

/*    @GetMapping("{userId}")
    public ResponseEntity findUserById(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(userId));
    }*/



}
