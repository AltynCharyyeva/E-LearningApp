package com.example.part1.service.impl;

import com.example.part1.model.Instructor;
import com.example.part1.repository.InstructorRepository;
import com.example.part1.repository.LearnerRepository;
import com.example.part1.service.InstructorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final LearnerRepository learnerRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository, LearnerRepository learnerRepository){
        this.instructorRepository = instructorRepository;
        this.learnerRepository = learnerRepository;
    }

    @Override
    public List<Instructor> findAll(){
        return (List<Instructor>) instructorRepository.findAll();
    }

    @Override
    public Instructor saveInstructor(Instructor instructor){
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor updateInstructor(Long instructorId, Instructor instructor){
        boolean exists = instructorRepository.findById(instructorId).isPresent();
        if(exists){
            Instructor originalInstructor = instructorRepository.findById(instructorId).get();
            if(instructor.getName() != null){
                originalInstructor.setName(instructor.getName());
            }
            if(instructor.getEmail() != null){
                originalInstructor.setEmail(instructor.getEmail());
            }
            if(instructor.getPassword() != null){
                originalInstructor.setPassword(instructor.getPassword());
            }
            if(instructor.getUsername() != null){
                originalInstructor.setUsername(instructor.getUsername());
            }
            return instructorRepository.save(originalInstructor);
        }
        return null;

    }

    @Override
    public boolean deleteInstructor(Long instructorId){
        boolean exists = instructorRepository.findById(instructorId).isPresent();
        if(exists){
            instructorRepository.deleteById(instructorId);
            return true;
        }
        return false;
    }



}
