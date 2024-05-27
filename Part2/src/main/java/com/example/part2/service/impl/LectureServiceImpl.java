package com.example.part2.service.impl;

import com.example.part2.model.Lecture;
import com.example.part2.repository.LectureRepository;
import com.example.part2.service.LectureService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    public LectureServiceImpl(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }

    @Override
    public List<Lecture> findAll(){
        return (List<Lecture>) lectureRepository.findAll();
    }

    @Override
    public Lecture saveLecture(Lecture lecture){
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture updateLecture(Long lectureId, Lecture lecture){
        boolean exists = lectureRepository.findById(lectureId).isPresent();
        if(exists){
            Lecture originalLecture = lectureRepository.findById(lectureId).get();
            if(lecture.getName() != null){
                originalLecture.setName(lecture.getName());
            }
            return lectureRepository.save(originalLecture);
        }
        return null;
    }

    @Override
    public boolean deleteLecture(Long lectureId){
        boolean exists = lectureRepository.findById(lectureId).isPresent();
        if(exists){
            lectureRepository.deleteById(lectureId);
            return true;
        }
        return false;
    }

    public Lecture findLectureById(Long id){
        return lectureRepository.findById(id).orElseThrow();
    }



}
