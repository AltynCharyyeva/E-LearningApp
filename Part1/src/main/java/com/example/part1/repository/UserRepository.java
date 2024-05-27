package com.example.part1.repository;

import com.example.part1.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstByUsernameAndPassword(String username, String password);

    boolean existsUserByUsername(String email);
}