package com.example.part2.service.impl;
import com.example.part2.constants.FileType;
import com.example.part2.dto.*;
import com.example.part2.exceptions.ApiExceptionResponse;
import com.example.part2.exporter.FileExporter;
import com.example.part2.exporter.TXTFileExporter;
import com.example.part2.exporter.XMLFileExporter;
import com.example.part2.mapper.UserMapper;
import com.example.part2.model.User;
import com.example.part2.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import com.example.part2.repository.UserRepository;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;

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
//
//    public User register(User user) throws ApiExceptionResponse {
//        // Check if the username is already taken
//        if (userRepository.existsUserByUsername(user.getUsername())) {
//            ArrayList<String> errors = new ArrayList<>();
//            errors.add("Username " + user.getUsername() + " or  is already taken");
//
//            throw ApiExceptionResponse.builder()
//                    .errors(errors)
//                    .message("Username already exists")
//                    .status(HttpStatus.BAD_REQUEST)
//                    .build();
//        }
//
//        // Create a new User object
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getPassword());
//        newUser.setEmail(user.getEmail());
//        // Set any other properties as needed
//
//        // Save the new user
//        User savedUser = userRepository.save(newUser);
//
//        // Return SuccessfulRegisterDTO
//        return savedUser;
//    }


    @Override
    public UserDTO register(UserCreationDTO user) {
        User entity = UserMapper.toCreationEntity(user);
        //entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity = userRepository.save(entity);
        return UserMapper.toDto(entity);
    }


    @Override
    public String exportUserDetails(Long userId, String fileType) {
        User user = this.findUserById(userId);
        FileExporter fileExporter;
        if (fileType.equals(FileType.XML)) {
            fileExporter = new XMLFileExporter();
            return fileExporter.exportData(user);
        } else if (fileType.equals(FileType.TXT)) {
            fileExporter = new TXTFileExporter();
            return fileExporter.exportData(user);
        }
        return null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User loginUser(String username, String password){
        User user = userRepository.findByUsername(username);

        if(user == null)
            return null;
        if(user.getPassword().equals(password))
            return user;
        else
            return null;
    }

    public User getUserByToken(String token){

        User user = userRepository.findByToken(token);
        if(user == null){
            return null;
        }
        else{
            return user;
        }

    }

    public void logout(User user){
        user.setToken(null);
        userRepository.save(user);
    }

}
