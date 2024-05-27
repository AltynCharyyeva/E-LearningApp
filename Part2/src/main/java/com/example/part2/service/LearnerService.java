package com.example.part2.service;

import com.example.part2.dto.BookmarkDTO;
import com.example.part2.dto.TakeCourseAddition;
import com.example.part2.exceptions.ApiExceptionResponse;
import com.example.part2.model.Learner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Include this to the Spring Container which means create instances of this object
public interface LearnerService {

    List<Learner> findAll();
    //Learner findLearnerById(Long id);
    //Learner findOwnerByName(String name) throws ApiExceptionResponse;
    //Learner findOwnerByUsername(String username) throws ApiExceptionResponse;
    //Learner saveLearner(Learner learner);
    Learner updateLearner (Long learnerId, Learner learner);
    boolean deleteLearner(Long id);
    Learner findLearnerByName(String name) throws ApiExceptionResponse;
    Learner takeCourse(TakeCourseAddition dto);

    Learner bookmarkLecture(BookmarkDTO bookmarkDTO);
}
