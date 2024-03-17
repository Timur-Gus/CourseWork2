package com.course2.courseWork.repository;

import com.course2.courseWork.myException.QuestionAlreadyAddedException;
import com.course2.courseWork.myException.QuestionNotFound;
import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.course2.courseWork.constants.ConstantsQuestionTest.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JavaQuestionRepositoryTest {
    JavaQuestionRepository javaQuestionRepository;

    @BeforeEach
    void init() {
        javaQuestionRepository = new JavaQuestionRepository();
    }

    @Test
    void addPositive() {
        Question actual = javaQuestionRepository.add(QUESTION_1, ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void alreadyAdded() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    javaQuestionRepository.add(QUESTION_1, ANSWER_1);
                    javaQuestionRepository.add(QUESTION_1, ANSWER_1);
                });
    }

    @Test
    void answerAndQuestionAreEquals() {

        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        javaQuestionRepository.add("Вопрос", "Вопрос")
                );
    }

    @Test
    void addObject() {
        Question actual = javaQuestionRepository.add(QUESTION_AND_ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void objectAlreadyAdded() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    javaQuestionRepository.add(QUESTION_AND_ANSWER_1);
                    javaQuestionRepository.add(QUESTION_AND_ANSWER_1);
                });

    }

    @Test
    void addObjectAnswerAndQuestionAreEquals() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        javaQuestionRepository.add(new Question("Вопрос", "Вопрос"))
                );
    }

    @Test
    void remove() {
        javaQuestionRepository.add(QUESTION_AND_ANSWER_1);
        javaQuestionRepository.remove(QUESTION_AND_ANSWER_1);
        assertFalse(javaQuestionRepository.getAll().contains(QUESTION_AND_ANSWER_1));
    }

    @Test
    void removeNotFound() {
        QuestionNotFound questionNotFound = assertThrows(QuestionNotFound.class, () -> {
            javaQuestionRepository.remove(QUESTION_AND_ANSWER_1);
        });
    }

    @Test
    void getAll() {
        javaQuestionRepository.add(QUESTION_AND_ANSWER_1);
        javaQuestionRepository.add(QUESTION_AND_ANSWER_2);
        javaQuestionRepository.add(QUESTION_AND_ANSWER_3);
        Collection<Question> actual = javaQuestionRepository.getAll();
        assertIterableEquals(QUESTIONS, actual);
    }

}