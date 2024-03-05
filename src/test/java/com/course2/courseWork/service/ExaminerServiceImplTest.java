package com.course2.courseWork.service;

import com.course2.courseWork.myException.LargeNumberException;
import com.course2.courseWork.repository.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static com.course2.courseWork.constants.ConstantsQuestionTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService questionServiceMock;
    @InjectMocks
    private ExaminerServiceImpl exam;

    @BeforeEach
    void init() {
        when(questionServiceMock.getAll())
                .thenReturn(QUESTIONS);
    }

    @Test
    void getRandomQuestionsTest() {
        when(questionServiceMock.getRandomQuestion()).thenReturn(QUESTION_AND_ANSWER_1);
        List<Question> expected = new ArrayList<>(List.of(QUESTION_AND_ANSWER_1));
        Collection<Question> actual = exam.getQuestions(1);
        System.out.println(exam.getQuestions(1));
        assertIterableEquals(expected,actual);
    }

    @Test
    void getRandomQuestionNegative() {
        LargeNumberException largeNumberException = assertThrows(LargeNumberException.class, () ->
                exam.getQuestions(10)
        );
    }

}