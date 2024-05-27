package com.example.part1.repository;

import com.example.part1.model.Learner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends CrudRepository<Learner, Long> {
    Learner findFirstByName(String name);
    //Learner findFirstByUsernameAndPassword(String username, String password);
    //Learner findFirstByUsername(String username);
}
