package com.course2.courseWork.service;

import com.course2.courseWork.myException.LargeNumberException;
import com.course2.courseWork.myInterface.ExaminerService;
import com.course2.courseWork.repository.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService questionService;

    public ExaminerServiceImpl(JavaQuestionService questionService){
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if(questionService.getAll().size() < amount){
            throw new LargeNumberException();
        }
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; ) {
            Question question = questionService.getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
                i++;
            }
        }
        return questions;
    }
}
