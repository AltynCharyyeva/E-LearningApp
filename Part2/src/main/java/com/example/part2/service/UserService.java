package com.example.part2.service;

import com.example.part2.dto.*;
import com.example.part2.exceptions.ApiExceptionResponse;
import com.example.part2.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    SuccessfulLoginDTO login(AuthDTO dto) throws ApiExceptionResponse;
    UserDTO register(UserCreationDTO user);
    User findUserById(Long userId);

     void saveUser(User user);
     void logout(User user);
     User loginUser(String username, String password);
     User getUserByToken(String token);

    public String exportUserDetails(Long userId, String fileType);

}
