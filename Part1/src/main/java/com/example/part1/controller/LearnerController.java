package com.example.part1.controller;

import com.example.part1.dto.TakeCourseAddition;
import com.example.part1.exceptions.ApiExceptionResponse;
import com.example.part1.model.Learner;
import com.example.part1.service.LearnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/learners")
@CrossOrigin("http://localhost:3000/")
public class LearnerController {
    // Dependency injection, we don't create a new instance with new, rather
    // we pass it to the constructor for loose coupling
    private final LearnerService learnerService;

    public LearnerController(LearnerService learnerService){
        this.learnerService = learnerService;
    }

    @GetMapping
    public ResponseEntity findAllLearners(){
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.findAll());
    }


    // Endpoint to update learner's name
    @PostMapping ("/update/{learnerId}")
    public ResponseEntity updateLearner(@PathVariable Long learnerId, @RequestBody Learner updatedLearner) {
        Learner learner = learnerService.updateLearner(learnerId, updatedLearner);
        if (learner != null) {
            return new ResponseEntity<>(learner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{learnerId}")
    public ResponseEntity deleteLearner(@PathVariable Long learnerId) {
        boolean deleted = learnerService.deleteLearner(learnerId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity createLearner(@RequestBody Learner learner) {
        Learner createdLearner = learnerService.saveLearner(learner);
        if (createdLearner != null) {
            return new ResponseEntity<>(createdLearner, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity findLearnerByName(@PathVariable String name) throws ApiExceptionResponse{
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.findLearnerByName(name));
    }

    @PutMapping("take_course")
    public ResponseEntity takeCourse(@RequestBody TakeCourseAddition dto) {
        try {
            Learner learner = learnerService.takeCourse(dto);
            return new ResponseEntity<>(learner, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Invalid course or learner ID", HttpStatus.BAD_REQUEST);
        }
    }




}
