package com.example.part2.mapper;


import com.example.part2.dto.UserCreationDTO;
import com.example.part2.dto.UserDTO;
import com.example.part2.model.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .id(dto.getId())
                .build();
    }

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .id(user.getId())
                .build();
    }

    public static User toCreationEntity(UserCreationDTO dto) {
        return User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

}
