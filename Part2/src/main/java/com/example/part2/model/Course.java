package com.example.part2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    // FetchType.EAGER - we want to fetch the lecture information when we access Tutorial, costly operation
    // CascadeType.ALL - when we delete the course the lectures of it will also be deleted
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private List<Lecture> lectures;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Learner> learnerSet = new HashSet<>();



}
