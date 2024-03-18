package com.course2.courseWork.service;

import com.course2.courseWork.repository.JavaQuestionRepository;
import model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {
    private final JavaQuestionRepository questionRepository;

    public JavaQuestionService() {
        questionRepository = new JavaQuestionRepository();
    }

    public JavaQuestionService(JavaQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
