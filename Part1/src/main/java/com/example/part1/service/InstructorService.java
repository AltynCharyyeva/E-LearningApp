package com.example.part1.service;

import com.example.part1.model.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> findAll();
    Instructor saveInstructor(Instructor instructor);
    Instructor updateInstructor(Long instructorId, Instructor instructor);
    boolean deleteInstructor(Long instructorId);
}
