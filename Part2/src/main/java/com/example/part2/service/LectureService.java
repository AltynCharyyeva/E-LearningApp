package com.example.part2.service;

import com.example.part2.model.Lecture;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LectureService {
    List<Lecture> findAll();
    Lecture saveLecture(Lecture lecture);

    Lecture updateLecture(Long lectureId, Lecture lecture);
    boolean deleteLecture(Long lectureId);
    Lecture findLectureById(Long id);
}
