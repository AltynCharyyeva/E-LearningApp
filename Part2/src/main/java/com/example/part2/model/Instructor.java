package com.example.part2.model;
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
public class Instructor extends User{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    private String firstName;
    private String lastName;

    // FetchType.LAZY - we don't want to get info about all courses the instructor teaches when access him/her
    // CascadeType.PERSIST - we want the courses persist even if when we delete the instructor, bcs we might get another instructor
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private List <GuidedCourse> teachingCourses;


    @ManyToMany
    @JoinTable(
            name = "learners_and_instructors",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "learner_id")
    )
    Set<Learner> myStudents = new HashSet<>();

}
