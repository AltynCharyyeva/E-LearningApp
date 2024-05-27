package com.example.part2.repository;

import com.example.part2.model.Learner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends CrudRepository<Learner, Long> {
    Learner findLearnerByUsername(String username);
    Learner findFirstByUsernameAndPassword(String username, String password);
    //Learner findFirstByUsername(String username);
}
