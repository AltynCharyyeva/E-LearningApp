package com.example.part1.service.impl;

import com.example.part1.dto.TakeLectureAddition;
import com.example.part1.model.Course;
import com.example.part1.model.Lecture;
import com.example.part1.repository.CourseRepository;
import com.example.part1.repository.LectureRepository;
import com.example.part1.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;

    public CourseServiceImpl(CourseRepository courseRepository, LectureRepository lectureRepository){
        this.courseRepository = courseRepository;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll(){
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long courseId, Course course){
        boolean exists = courseRepository.findById(courseId).isPresent();
        if(exists){
            Course originalCourse = courseRepository.findById(courseId).get();
            if(course.getName() != null){
                originalCourse.setName(course.getName());
            }
            if(course.getDescription() != null){
                originalCourse.setDescription(course.getDescription());
            }
            return courseRepository.save(originalCourse);
        }
        return null;
    }

    public Course findCourseById(Long id){
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean deleteCourse(Long courseId){
        boolean exists = courseRepository.findById(courseId).isPresent();
        if(exists){
            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    public Course addLecture(Long courseId, TakeLectureAddition dto){
        List<Lecture> lectures;
        Course course = courseRepository.findById(courseId).orElseThrow();
        Lecture lecture = lectureRepository.findById(dto.getLectureId()).orElseThrow();


        lectures = course.getLectures();
        lectures.add(lecture);

        course.setLectures(lectures);
        return courseRepository.save(course);
    }
}
