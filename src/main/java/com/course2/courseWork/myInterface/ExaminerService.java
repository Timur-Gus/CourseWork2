package com.course2.courseWork.myInterface;

import com.course2.courseWork.repository.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);

}
