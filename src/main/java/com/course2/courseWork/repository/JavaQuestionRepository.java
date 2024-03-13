package com.course2.courseWork.repository;

import com.course2.courseWork.myException.QuestionAlreadyAddedException;
import com.course2.courseWork.myException.QuestionNotFound;
import com.course2.courseWork.myInterface.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final List<Question> questions;

    public JavaQuestionRepository() {
        this.questions = new ArrayList<>();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
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
        if (questions.remove(question)) {
            return question;
        }
        throw new QuestionNotFound();
    }

    @Override
    public List<Question> getAll() {
        return Collections.unmodifiableList(questions);
    }


}
