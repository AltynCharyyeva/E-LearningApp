package com.example.part2;

import com.example.part2.service.CategoryService;
import com.example.part2.service.CourseService;
import com.example.part2.service.LearnerService;
import com.example.part2.service.LectureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class A2Application {

    public static void main(String[] args) {
        SpringApplication.run(A2Application.class, args);
    }

    @Bean
        // Bean - objects in Spring Container
    CommandLineRunner init(LearnerService learnerService, CategoryService categoryService,
                           CourseService courseService, LectureService lectureService){
        return args -> {

        };
    }
}

