package com.course2.courseWork.service;

import com.course2.courseWork.myException.QuestionAlreadyAddedException;
import com.course2.courseWork.myException.QuestionNotFound;
import com.course2.courseWork.repository.MathQuestionRepository;
import com.course2.courseWork.repository.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static com.course2.courseWork.constants.ConstantsQuestionTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    @Mock
    MathQuestionRepository mathQuestionRepository;
    @InjectMocks
    MathQuestionService mathQuestionService;

    @Test
    void addPositive() {
        when(mathQuestionRepository.add(QUESTION_1, ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        Question actual = mathQuestionService.add(QUESTION_1, ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void alreadyAdded() {
        when(mathQuestionRepository.add(QUESTION_1, ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        when(mathQuestionRepository.add(QUESTION_1, ANSWER_1))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    mathQuestionService.add(QUESTION_1, ANSWER_1);
                    mathQuestionService.add(QUESTION_1, ANSWER_1);
                });
    }

    @Test
    void answerAndQuestionAreEquals() {
        when(mathQuestionRepository.add("Вопрос", "Вопрос"))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        mathQuestionService.add("Вопрос", "Вопрос")
                );
    }

    @Test
    void addObject() {
        when(mathQuestionRepository.add(QUESTION_AND_ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        Question actual = mathQuestionService.add(QUESTION_AND_ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void objectAlreadyAdded() {
        when(mathQuestionRepository.add(QUESTION_AND_ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        when(mathQuestionRepository.add(QUESTION_AND_ANSWER_1))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    mathQuestionService.add(QUESTION_AND_ANSWER_1);
                    mathQuestionService.add(QUESTION_AND_ANSWER_1);
                });

    }

    @Test
    void addObjectAnswerAndQuestionAreEquals() {
        when(mathQuestionRepository.add(new Question("Вопрос", "Вопрос")))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        mathQuestionService.add(new Question("Вопрос", "Вопрос"))
                );
    }

    @Test
    void remove() {
        when(mathQuestionRepository.add(QUESTION_AND_ANSWER_1)).thenReturn(QUESTION_AND_ANSWER_1);
        when(mathQuestionRepository.remove(QUESTION_AND_ANSWER_1)).thenReturn(QUESTION_AND_ANSWER_1);
        when(mathQuestionRepository.getAll()).thenReturn(QUESTIONS1);
        mathQuestionService.add(QUESTION_AND_ANSWER_1);
        mathQuestionService.remove(QUESTION_AND_ANSWER_1);
        assertFalse(mathQuestionService.getAll().contains(QUESTION_AND_ANSWER_1));
    }

    @Test
    void removeNotFound() {
        when(mathQuestionRepository.remove(QUESTION_AND_ANSWER_1)).thenThrow(QuestionNotFound.class);
        QuestionNotFound questionNotFound = assertThrows(QuestionNotFound.class, () -> {
            mathQuestionService.remove(QUESTION_AND_ANSWER_1);
        });
    }

    @Test
    void getAll() {
        when(mathQuestionRepository.getAll()).thenReturn(QUESTIONS);
        Collection<Question> actual = mathQuestionService.getAll();
        assertIterableEquals(QUESTIONS, actual);
    }

    @Test
    void getRandomQuestion() {
        when(mathQuestionRepository.getAll()).thenReturn(QUESTIONS);
        Question actual = mathQuestionService.getRandomQuestion();
        assertTrue(mathQuestionService.getAll().contains(actual));
    }
}