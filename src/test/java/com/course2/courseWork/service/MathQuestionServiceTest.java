package com.course2.courseWork.service;

import com.course2.courseWork.myException.NotImplementedException;
import model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static com.course2.courseWork.constants.ConstantsQuestionTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    @InjectMocks
    MathQuestionService mathQuestionService;
    @Mock
    Random randomMock;
    @Test
    void addTest() {
        NotImplementedException notImplementedException = assertThrows(NotImplementedException.class,
                () -> {
                    mathQuestionService.add(QUESTION_1, QUESTION_2);
                });
    }

    @Test
    void addObjectTest() {
        NotImplementedException notImplementedException = assertThrows(NotImplementedException.class,
                () -> {
                    mathQuestionService.add(QUESTION_AND_ANSWER_1);
                });
    }


    @Test
    void remove() {
        NotImplementedException notImplementedException = assertThrows(NotImplementedException.class,
                () -> {
                    mathQuestionService.remove(QUESTION_AND_ANSWER_1);
                });
    }



    @Test
    void getAll() {
        NotImplementedException notImplementedException = assertThrows(NotImplementedException.class,
                () -> {
                    mathQuestionService.getAll();
                });
    }

    @Test
    void getRandomQuestion() {
        when(randomMock.nextInt(100)).thenReturn(10);
        when(randomMock.nextInt(100)).thenReturn(10);
        when(randomMock.nextInt(4)).thenReturn(0);
        Question actual = mathQuestionService.getRandomQuestion();
        Question expected = new Question(10 + " + " + 10, 20 + "");
        assertEquals(expected, actual);
    }
}