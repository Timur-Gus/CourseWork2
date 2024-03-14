package com.course2.courseWork.service;

import com.course2.courseWork.myInterface.ExaminerService;
import com.course2.courseWork.myInterface.QuestionService;
import com.course2.courseWork.repository.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random random = new Random();
    private final List<QuestionService> questionServices;

    public ExaminerServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;

    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        return Stream.generate(this::getRandomQuestion)
                .distinct()
                .limit(amount)
                .toList();
    }

    private Question getRandomQuestion() {
        int ind = random.nextInt(questionServices.size());
        return questionServices.get(ind).getRandomQuestion();
    }
}
