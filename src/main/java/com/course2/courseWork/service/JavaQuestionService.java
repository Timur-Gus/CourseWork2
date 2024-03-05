package com.course2.courseWork.service;

import com.course2.courseWork.myException.QuestionAlreadyAddedException;
import com.course2.courseWork.myException.QuestionNotFound;
import com.course2.courseWork.myInterface.QuestionService;
import com.course2.courseWork.repository.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class JavaQuestionService implements QuestionService {
    private final List<Question> questions;

    public JavaQuestionService() {
        this.questions = new ArrayList<>();
    }


    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question, answer);
        if (!questions.contains(question1) && !question.equals(answer)) {
            questions.add(question1);
            return question1;
        }
        throw new QuestionAlreadyAddedException();
    }

    @Override
    public Question add(Question question) {
        if (!questions.contains(question) &&
                !question.getAnswer().equals(question.getQuestion())) {
            questions.add(question);
            return question;
        }
        throw new QuestionAlreadyAddedException();
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new QuestionNotFound();
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableList(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
}
