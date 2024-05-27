package com.example.part1.repository;

import com.example.part1.model.Lecture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LectureRepository extends CrudRepository<Lecture, Long> {
}
