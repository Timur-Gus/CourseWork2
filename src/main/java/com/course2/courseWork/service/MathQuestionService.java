package com.course2.courseWork.service;


import com.course2.courseWork.myException.NotImplementedException;
import com.course2.courseWork.myInterface.QuestionService;
import com.course2.courseWork.repository.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.Random;

@Service
@SessionScope
public class MathQuestionService implements QuestionService {

    @Override
    public Question add(String question, String answer) {
        throw new NotImplementedException();
    }

    @Override
    public Question add(Question question) {
        throw new NotImplementedException();
    }

    @Override
    public Question remove(Question question) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Question> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int operation = random.nextInt(4);
        return switch (operation) {
            case 0 -> new Question(a + " + " + b, a + b + "");
            case 1 -> new Question(a + " - " + b, a - b + "");
            case 2 -> new Question(a + " * " + b, a * b + "");
            case 3 -> new Question(a + " / " + b, (double) a / b + "");
            default -> throw new IllegalStateException();
        };
    }

}
