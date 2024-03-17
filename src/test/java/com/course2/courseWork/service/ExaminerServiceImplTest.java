package com.course2.courseWork.service;

import com.course2.courseWork.myException.LargeNumberException;
import model.Question;
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
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionServiceMock;
    @Mock
    private MathQuestionService mathQuestionServiceMock;
    @Mock
    private Random randomMock;

    @InjectMocks
    private ExaminerServiceImpl exam;

    @BeforeEach
    void init() {
        when(javaQuestionServiceMock.getAll())
                .thenReturn(QUESTIONS);
        when(javaQuestionServiceMock.getAll())
                .thenReturn(MATH_QUESTIONS);
    }

    @Test
    void getRandomQuestionsJavaTest() {
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(QUESTION_AND_ANSWER_1);
        when(randomMock.nextInt(2)).thenReturn(0);
        List<Question> expected = new ArrayList<>(List.of(QUESTION_AND_ANSWER_1));
        Collection<Question> actual = exam.getQuestions(1);
        assertIterableEquals(expected, actual);
    }

    @Test
    void getRandomQuestionsMathTest() {
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(MATH_QUESTION_AND_ANSWER);
        when(randomMock.nextInt(2)).thenReturn(1);
        List<Question> expected = new ArrayList<>(List.of(MATH_QUESTION_AND_ANSWER));
        Collection<Question> actual = exam.getQuestions(1);
        assertIterableEquals(expected, actual);
    }

    @Test
    void getRandomQuestionNegative() {
        LargeNumberException largeNumberException = assertThrows(LargeNumberException.class, () ->
                exam.getQuestions(10)
        );
    }

}