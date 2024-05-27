package com.example.part1;

import com.example.part1.service.CategoryService;
import com.example.part1.service.CourseService;
import com.example.part1.service.LearnerService;
import com.example.part1.service.LectureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Part1Application {

    public static void main(String[] args) {
        SpringApplication.run(Part1Application.class, args);
    }


    @Bean  // Bean - objects in Spring Container
    CommandLineRunner init(LearnerService learnerService, CategoryService categoryService,
                           CourseService courseService, LectureService lectureService){
        return args -> {
/*
            Course course1 = new Course();
            course1.setName("Bett kurs");

            Lecture lecture1 = new Lecture();
            lecture1.setName("Bett leksiya");

            courseService.saveCourse(course1);
            lectureService.saveLecture(lecture1);

            TakeLectureAddition dto = new TakeLectureAddition();
            dto.setCourseId(course1.getId());
            dto.setLectureId(lecture1.getId());

            courseService.addLecture(dto);*/

            //Learner learner1 = new Learner();
            //learner1.setUsername("Learner1");
            //learner1.setPassword("pngjnreekfmv");

            //learnerService.saveLearner(learner1);

/*
            Learner learner2 = new Learner();
            learner2.setUsername("Learner2");
            learner2.setPassword("lkdnfalpfae");

            Learner learner3 = new Learner();
            learner3.setUsername("Learner3");
            learner3.setPassword("kglsfgjalarita");


            learnerService.saveLearner(learner1);
            learnerService.saveLearner(learner2);
            learnerService.saveLearner(learner3);


            Category math = new Category();
            math.setCategoryName("MATH: HIGH SCHOOL & COLLEGE");

            Category science = new Category();
            science.setCategoryName("SCIENCE");

            categoryService.saveCategory(math);
            categoryService.saveCategory(science);


            Course course1 = new Course();
            course1.setName("High school biology");
            course1.setCategory_id(science.getId());
            course1.setDescription("9 units, 51 skills");


            Course course2 = new Course();
            course2.setName("High school chemistry");
            course2.setCategory_id(science.getId());
            course2.setDescription("10 units, 60 skills");


            Course course3 = new Course();
            course3.setName("Algebra 1");
            course3.setCategory_id(math.getId());
            course3.setDescription("15 units, 50 skills");

            courseRepository.save(course1);
            courseRepository.save(course2);
            courseRepository.save(course3);*/












        };
    }
}
