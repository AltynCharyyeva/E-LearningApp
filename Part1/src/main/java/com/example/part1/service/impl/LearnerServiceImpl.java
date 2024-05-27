package com.example.part1.service.impl;

import com.example.part1.dto.TakeCourseAddition;
import com.example.part1.model.Course;
import com.example.part1.exceptions.ApiExceptionResponse;
import com.example.part1.model.Learner;
import com.example.part1.repository.LearnerRepository;
import com.example.part1.repository.CourseRepository;
import com.example.part1.service.LearnerService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class LearnerServiceImpl implements LearnerService {
    private final LearnerRepository learnerRepository;
    private final CourseRepository courseRepository;

    public LearnerServiceImpl(LearnerRepository learnerRepository, CourseRepository courseRepository){
        this.learnerRepository = learnerRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Learner> findAll(){
        return (List<Learner>) learnerRepository.findAll();
    }

    @Override
    public Learner saveLearner(Learner learner) {
        return learnerRepository.save(learner);
    }


    @Override
    public Learner updateLearner(Long learnerId, Learner learner) {
        boolean exists = learnerRepository.findById(learnerId).isPresent();
        if (exists){
            Learner originalLearner = learnerRepository.findById(learnerId).get();
            if(learner.getUsername() != null){ // if the learner updates username
                originalLearner.setUsername(learner.getUsername());
            }
            if(learner.getEmail() != null){ // if the learner updates email
                originalLearner.setEmail(learner.getEmail());
            }
            if(learner.getName() != null){
                originalLearner.setName(learner.getName()); // if the learner updates name
            }
            if(learner.getSurname() != null){
                originalLearner.setSurname(learner.getSurname());
            }
            if(learner.getPassword() != null){ // if the learner updates password
                originalLearner.setPassword(learner.getPassword());
            }
            return learnerRepository.save(originalLearner);
        }
        return null;
    }

    @Override
    public boolean deleteLearner(Long id) {
        boolean exists = learnerRepository.findById(id).isPresent();
        if (exists) {
            learnerRepository.deleteById(id);
            return true; // Return true indicating successful deletion
        }
        return false; // Return false indicating learner not found or deletion failed
    }

    public Learner findLearnerByName(String name) throws ApiExceptionResponse{
        Learner learner = learnerRepository.findFirstByName(name);
        if(learner == null){
            throw ApiExceptionResponse.builder()
                    .errors(Collections.singletonList("No learner with name "+ name))
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return learner;
    }

    public Learner takeCourse(TakeCourseAddition dto){
        Set<Course> enrolledCourses;
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow();
        Learner learner = learnerRepository.findById(dto.getLearnerId()).orElseThrow();

        enrolledCourses = learner.getEnrolledCourses();
        enrolledCourses.add(course);

        learner.setEnrolledCourses(enrolledCourses);

        return learnerRepository.save(learner);
    }






}
