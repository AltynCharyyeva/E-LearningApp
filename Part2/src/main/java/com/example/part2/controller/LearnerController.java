package com.example.part2.controller;

import com.example.part2.dto.BookmarkDTO;
import com.example.part2.dto.TakeCourseAddition;
import com.example.part2.exceptions.ApiExceptionResponse;
import com.example.part2.model.Learner;
import com.example.part2.service.LearnerService;
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

//    @PostMapping("/save")
//    public ResponseEntity createLearner(@Valid @RequestBody UserDTO newLearner) {
//        return ResponseEntity.status(HttpStatus.OK).body(learnerService.saveLearner(newLearner))
//    }

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

    @PutMapping("bookmark")
    public ResponseEntity bookmarkLecture(@RequestBody BookmarkDTO dto) {
        try {
            Learner learner = learnerService.bookmarkLecture(dto);
            return new ResponseEntity<>(learner, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Invalid lecture or learner ID", HttpStatus.BAD_REQUEST);
        }
    }

/*    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }*/



}
