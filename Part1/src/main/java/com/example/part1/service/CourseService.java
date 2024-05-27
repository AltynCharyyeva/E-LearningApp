package com.example.part1.service;

import com.example.part1.dto.TakeLectureAddition;
import com.example.part1.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseService {
    List <Course> findAll();
    Course saveCourse(Course course);
    Course updateCourse(Long courseId, Course course);
    boolean deleteCourse(Long courseId);

    Course findCourseById(Long id);

    Course addLecture(Long courseId, TakeLectureAddition dto);
}
