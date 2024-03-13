package com.course2.courseWork.service;

import com.course2.courseWork.myInterface.QuestionService;
import com.course2.courseWork.repository.JavaQuestionRepository;
import com.course2.courseWork.repository.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.Random;

@Service
@SessionScope
public class JavaQuestionService implements QuestionService {
    JavaQuestionRepository questionRepository;

    public JavaQuestionService() {
        questionRepository = new JavaQuestionRepository();
    }

    @Override
    public Question add(String question, String answer) {
        return questionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        return questionRepository.getAll()
                .get(random.nextInt(questionRepository.getAll().size()));
    }
}
