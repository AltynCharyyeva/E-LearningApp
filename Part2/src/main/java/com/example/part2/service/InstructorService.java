package com.example.part2.service;

import com.example.part2.model.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> findAll();
    Instructor saveInstructor(Instructor instructor);
    Instructor updateInstructor(Long instructorId, Instructor instructor);
    boolean deleteInstructor(Long instructorId);
}
