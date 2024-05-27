package com.example.part2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;


    @NotNull(message = "email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9. _-]+@[a-zA-Z0-9. -]+\\.[a-zA-Z]{2,4}$", message = "email is not valid")
    private String email;


    @NotNull(message = "username is mandatory")
    @Size(min = 3, max = 20, message = "Last name must be between {min} and {max}")
    private String username;


    @NotNull(message = "password is mandatory")
    @Size(min = 5, max = 20, message = "Password must be between {min} and {max}")
    private String password;
}
