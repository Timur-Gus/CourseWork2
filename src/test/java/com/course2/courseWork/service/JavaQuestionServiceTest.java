package com.course2.courseWork.service;

import com.course2.courseWork.myException.QuestionAlreadyAddedException;
import com.course2.courseWork.myException.QuestionNotFound;
import com.course2.courseWork.repository.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static com.course2.courseWork.constants.ConstantsQuestionTest.*;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    JavaQuestionService javaQuestionService;

    @BeforeEach
    void init() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void addPositive() {
        Question actual = javaQuestionService.add(QUESTION_1, ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void alreadyAdded() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    javaQuestionService.add(QUESTION_1, ANSWER_1);
                    javaQuestionService.add(QUESTION_1, ANSWER_1);
                });
    }

    @Test
    void answerAndQuestionAreEquals() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        javaQuestionService.add("Вопрос", "Вопрос")
                );
    }

    @Test
    void addObject() {
        Question actual = javaQuestionService.add(QUESTION_AND_ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void objectAlreadyAdded() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    javaQuestionService.add(QUESTION_AND_ANSWER_1);
                    javaQuestionService.add(QUESTION_AND_ANSWER_1);
                });

    }

    @Test
    void addObjectAnswerAndQuestionAreEquals() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        javaQuestionService.add(new Question("Вопрос", "Вопрос"))
                );
    }

    @Test
    void remove() {
        javaQuestionService.add(QUESTION_AND_ANSWER_1);
        javaQuestionService.remove(QUESTION_AND_ANSWER_1);
        assertFalse(javaQuestionService.getAll().contains(QUESTION_AND_ANSWER_1));
    }

    @Test
    void removeNotFound() {
        QuestionNotFound questionNotFound = assertThrows(QuestionNotFound.class, () -> {
            javaQuestionService.remove(QUESTION_AND_ANSWER_1);
        });
    }

    @Test
    void getAll() {
        javaQuestionService.add(QUESTION_AND_ANSWER_1);
        javaQuestionService.add(QUESTION_AND_ANSWER_2);
        javaQuestionService.add(QUESTION_AND_ANSWER_3);
        Collection<Question> actual = javaQuestionService.getAll();
        assertIterableEquals(QUESTIONS, actual);
    }

    @Test
    void getRandomQuestion() {
        javaQuestionService.add(QUESTION_AND_ANSWER_1);
        javaQuestionService.add(QUESTION_AND_ANSWER_2);
        javaQuestionService.add(QUESTION_AND_ANSWER_3);
        Question actual = javaQuestionService.getRandomQuestion();
        assertTrue(javaQuestionService.getAll().contains(actual));
    }
}