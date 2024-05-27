package com.example.part1.controller;

import com.example.part1.dto.TakeLectureAddition;
import com.example.part1.model.Course;
import com.example.part1.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/courses")
@CrossOrigin("http://localhost:3000/")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity findAllCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @GetMapping("{courseId}")
    public ResponseEntity findCourseById(@PathVariable Long courseId){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findCourseById(courseId));
    }

    @PostMapping("save")
    public ResponseEntity createCourse(@RequestBody Course course){
        Course createdCourse = courseService.saveCourse(course);
        if(createdCourse != null){
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/{courseId}")
    public ResponseEntity updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse){
        Course course = courseService.updateCourse(courseId, updatedCourse);
        if(course != null){
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("delete/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable Long courseId){
        boolean deleted = courseService.deleteCourse(courseId);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{courseId}/add_lecture")
    public ResponseEntity addLecture(@PathVariable Long courseId, @RequestBody TakeLectureAddition dto){
        try {
            Course course = courseService.addLecture(courseId, dto);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Invalid lecture or course ID", HttpStatus.BAD_REQUEST);
        }
    }
}

