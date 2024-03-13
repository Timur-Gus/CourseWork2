package com.course2.courseWork.repository;

import com.course2.courseWork.myException.QuestionAlreadyAddedException;
import com.course2.courseWork.myException.QuestionNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static com.course2.courseWork.constants.ConstantsQuestionTest.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MathQuestionRepositoryTest {
    MathQuestionRepository mathQuestionRepository;

    @BeforeEach
    void init() {
        mathQuestionRepository = new MathQuestionRepository();
    }

    @Test
    void addPositive() {
        Question actual = mathQuestionRepository.add(QUESTION_1, ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void alreadyAdded() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    mathQuestionRepository.add(QUESTION_1, ANSWER_1);
                    mathQuestionRepository.add(QUESTION_1, ANSWER_1);
                });
    }

    @Test
    void answerAndQuestionAreEquals() {

        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        mathQuestionRepository.add("Вопрос", "Вопрос")
                );
    }

    @Test
    void addObject() {
        Question actual = mathQuestionRepository.add(QUESTION_AND_ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void objectAlreadyAdded() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    mathQuestionRepository.add(QUESTION_AND_ANSWER_1);
                    mathQuestionRepository.add(QUESTION_AND_ANSWER_1);
                });

    }

    @Test
    void addObjectAnswerAndQuestionAreEquals() {
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        mathQuestionRepository.add(new Question("Вопрос", "Вопрос"))
                );
    }

    @Test
    void remove() {
        mathQuestionRepository.add(QUESTION_AND_ANSWER_1);
        mathQuestionRepository.remove(QUESTION_AND_ANSWER_1);
        assertFalse(mathQuestionRepository.getAll().contains(QUESTION_AND_ANSWER_1));
    }

    @Test
    void removeNotFound() {
        QuestionNotFound questionNotFound = assertThrows(QuestionNotFound.class, () -> {
            mathQuestionRepository.remove(QUESTION_AND_ANSWER_1);
        });
    }

    @Test
    void getAll() {
        mathQuestionRepository.add(QUESTION_AND_ANSWER_1);
        mathQuestionRepository.add(QUESTION_AND_ANSWER_2);
        mathQuestionRepository.add(QUESTION_AND_ANSWER_3);
        Collection<Question> actual = mathQuestionRepository.getAll();
        assertIterableEquals(QUESTIONS, actual);
    }
}