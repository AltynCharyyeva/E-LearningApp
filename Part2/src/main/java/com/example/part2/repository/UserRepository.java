package com.example.part2.repository;

import com.example.part2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstByUsernameAndPassword(String username, String password);

    boolean existsUserByUsername(String email);
    User findByUsername(String username);
    User findByToken(String token);
}