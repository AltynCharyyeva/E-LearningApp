package com.example.part1.controller;

import com.example.part1.model.Instructor;
import com.example.part1.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity findAllInstructors(){
        return ResponseEntity.status(HttpStatus.OK).body(instructorService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity createInstructor(@RequestBody Instructor instructor){
        Instructor createdInstructor = instructorService.saveInstructor(instructor);
        if(createdInstructor != null){
            return new ResponseEntity(createdInstructor, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/update/{instructorId}")
    public ResponseEntity updateInstructor(@PathVariable Long instructorId, @RequestBody Instructor updatedInstructor) {
        Instructor instructor = instructorService.updateInstructor(instructorId, updatedInstructor);
        if (instructor != null) {
            return new ResponseEntity<>(instructor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{instructorId}")
    public ResponseEntity deleteInstructor(@PathVariable Long instructorId) {
        boolean deleted = instructorService.deleteInstructor(instructorId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
