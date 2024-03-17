package com.course2.courseWork.service;

import com.course2.courseWork.myException.QuestionAlreadyAddedException;
import com.course2.courseWork.myException.QuestionNotFound;
import com.course2.courseWork.repository.JavaQuestionRepository;
import model.Question;
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
class JavaQuestionServiceTest {
    @InjectMocks
    JavaQuestionService javaQuestionService;
    @Mock
    JavaQuestionRepository javaQuestionRepository;


    @Test
    void addPositive() {
        when(javaQuestionRepository.add(QUESTION_1, ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        Question actual = javaQuestionService.add(QUESTION_1, ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void alreadyAdded() {
        when(javaQuestionRepository.add(QUESTION_1, ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        when(javaQuestionRepository.add(QUESTION_1, ANSWER_1))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    javaQuestionService.add(QUESTION_1, ANSWER_1);
                    javaQuestionService.add(QUESTION_1, ANSWER_1);
                });
    }

    @Test
    void answerAndQuestionAreEquals() {
        when(javaQuestionRepository.add("Вопрос", "Вопрос"))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        javaQuestionService.add("Вопрос", "Вопрос")
                );
    }

    @Test
    void addObject() {
        when(javaQuestionRepository.add(QUESTION_AND_ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        Question actual = javaQuestionService.add(QUESTION_AND_ANSWER_1);
        assertEquals(QUESTION_AND_ANSWER_1, actual);
    }

    @Test
    void objectAlreadyAdded() {
        when(javaQuestionRepository.add(QUESTION_AND_ANSWER_1))
                .thenReturn(QUESTION_AND_ANSWER_1);
        when(javaQuestionRepository.add(QUESTION_AND_ANSWER_1))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () -> {
                    javaQuestionService.add(QUESTION_AND_ANSWER_1);
                    javaQuestionService.add(QUESTION_AND_ANSWER_1);
                });

    }

    @Test
    void addObjectAnswerAndQuestionAreEquals() {
        when(javaQuestionRepository.add(new Question("Вопрос", "Вопрос")))
                .thenThrow(QuestionAlreadyAddedException.class);
        QuestionAlreadyAddedException questionAlreadyAddedException =
                assertThrows(QuestionAlreadyAddedException.class, () ->
                        javaQuestionService.add(new Question("Вопрос", "Вопрос"))
                );
    }

    @Test
    void remove() {
        when(javaQuestionRepository.add(QUESTION_AND_ANSWER_1)).thenReturn(QUESTION_AND_ANSWER_1);
        when(javaQuestionRepository.remove(QUESTION_AND_ANSWER_1)).thenReturn(QUESTION_AND_ANSWER_1);
        when(javaQuestionRepository.getAll()).thenReturn(QUESTIONS1);
        javaQuestionService.add(QUESTION_AND_ANSWER_1);
        javaQuestionService.remove(QUESTION_AND_ANSWER_1);
        assertFalse(javaQuestionService.getAll().contains(QUESTION_AND_ANSWER_1));
    }

    @Test
    void removeNotFound() {
        when(javaQuestionRepository.remove(QUESTION_AND_ANSWER_1)).thenThrow(QuestionNotFound.class);
        QuestionNotFound questionNotFound = assertThrows(QuestionNotFound.class, () -> {
            javaQuestionService.remove(QUESTION_AND_ANSWER_1);
        });
    }

    @Test
    void getAll() {
        when(javaQuestionRepository.getAll()).thenReturn(QUESTIONS);
        Collection<Question> actual = javaQuestionService.getAll();
        assertIterableEquals(QUESTIONS, actual);
    }

    @Test
    void getRandomQuestion() {
        when(javaQuestionRepository.getAll()).thenReturn(QUESTIONS);
        Question actual = javaQuestionService.getRandomQuestion();
        assertTrue(javaQuestionService.getAll().contains(actual));
    }
}