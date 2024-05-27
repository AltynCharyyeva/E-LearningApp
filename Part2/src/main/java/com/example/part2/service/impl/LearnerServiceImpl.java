package com.example.part2.service.impl;

import com.example.part2.dto.*;
import com.example.part2.model.Course;
import com.example.part2.exceptions.ApiExceptionResponse;
import com.example.part2.model.Learner;
import com.example.part2.model.Lecture;
import com.example.part2.repository.LearnerRepository;
import com.example.part2.repository.CourseRepository;
import com.example.part2.repository.LectureRepository;
import com.example.part2.service.LearnerService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class LearnerServiceImpl implements LearnerService {
    private final LearnerRepository learnerRepository;
    private final CourseRepository courseRepository;
    private LectureRepository lectureRepository;

    public LearnerServiceImpl(
            LearnerRepository learnerRepository,
            CourseRepository courseRepository,
            LectureRepository lectureRepository){
        this.learnerRepository = learnerRepository;
        this.courseRepository = courseRepository;
        this.lectureRepository=lectureRepository;
    }

    @Override
    public List<Learner> findAll(){
        return (List<Learner>) learnerRepository.findAll();
    }

//    @Override
//    public Learner saveLearner(UserDTO learner) {
//        Learner entity = LearnerMapper.to
//    }


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
            if(learner.getFirstName() != null){
                originalLearner.setFirstName(learner.getFirstName()); // if the learner updates name
            }
            if(learner.getLastName() != null){
                originalLearner.setLastName(learner.getLastName());
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
        Learner learner = learnerRepository.findLearnerByUsername(name);
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
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        Learner learner = learnerRepository.findLearnerByUsername(dto.getUsername());

        enrolledCourses = learner.getEnrolledCourses();
        enrolledCourses.add(course);

        learner.setEnrolledCourses(enrolledCourses);

        return learnerRepository.save(learner);
    }

    @Override
    public Learner bookmarkLecture(BookmarkDTO bookmarkDTO) {
        Set <Lecture> bookmarks;
        Lecture lecture = lectureRepository.findById(bookmarkDTO.getLectureId())
                .orElseThrow(() -> new NoSuchElementException("Lecture not found"));
        Learner learner = learnerRepository.findLearnerByUsername(bookmarkDTO.getUsername());


        bookmarks = learner.getBookmarks();
        bookmarks.add(lecture);

        learner.setBookmarks(bookmarks);

        return learnerRepository.save(learner);
    }

}
