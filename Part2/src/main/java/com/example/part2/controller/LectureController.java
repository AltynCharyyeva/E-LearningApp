package com.example.part2.controller;


import com.example.part2.model.Lecture;
import com.example.part2.service.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
@CrossOrigin("http://localhost:3000/")
public class LectureController {

    private LectureService lectureService;

    public LectureController(LectureService lectureService){
        this.lectureService = lectureService;
    }

    @GetMapping
    public ResponseEntity findAllLecture(){
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.findAll());
    }

    @PostMapping("save")
    public ResponseEntity createLecture(@RequestBody Lecture lecture){
        Lecture createdLecture = lectureService.saveLecture(lecture);
        if(createdLecture != null){
            return new ResponseEntity<>(createdLecture, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/{lectureId}")
    public ResponseEntity updateLecture(@PathVariable Long lectureId, @RequestBody Lecture updatedLecture){
        Lecture lecture = lectureService.updateLecture(lectureId, updatedLecture);
        if(lecture != null){
            return new ResponseEntity<>(lecture, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("delete/{lectureId}")
    public ResponseEntity deleteLecture(@PathVariable Long lectureId){
        boolean deleted = lectureService.deleteLecture(lectureId);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{lectureId}")
    public ResponseEntity findLectureById(@PathVariable Long lectureId){
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.findLectureById(lectureId));
    }
}

