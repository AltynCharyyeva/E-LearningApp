package com.example.assignment1.service;

import com.example.assignment1.repository.CourseRepository;
import com.example.assignment1.repository.LearnerRepository;
import com.example.assignment1.service.impl.LearnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class LearnerServiceImplTest {

    private LearnerServiceImpl learnerService;

    @Mock
    private LearnerRepository learnerRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp(){
        initMocks(this);
        learnerService = new LearnerServiceImpl(learnerRepository, courseRepository);
    }

    //@Test
    //public void givenExistingLearner_whenFindLearner
}
