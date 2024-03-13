package com.course2.courseWork.myInterface;

import com.course2.courseWork.repository.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(String question, String answer);

    Question remove(Question question);

    Collection<Question> getAll();

    Question add(Question question);
}
