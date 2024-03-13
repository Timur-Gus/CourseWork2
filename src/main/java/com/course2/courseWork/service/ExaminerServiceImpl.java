package com.course2.courseWork.service;

import com.course2.courseWork.myException.LargeNumberException;
import com.course2.courseWork.myInterface.ExaminerService;
import com.course2.courseWork.myInterface.QuestionService;
import com.course2.courseWork.repository.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random random = new Random();
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;

    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if ((javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) < amount) {
            throw new LargeNumberException();
        }
        return Stream.generate(this::getRandomQuestion)
                .distinct()
                .limit(amount)
                .toList();
    }

    private Question getRandomQuestion() {
        int ind = random.nextInt(2);
        if (ind == 0) {
            return javaQuestionService.getRandomQuestion();
        }
        return mathQuestionService.getRandomQuestion();
    }
}
