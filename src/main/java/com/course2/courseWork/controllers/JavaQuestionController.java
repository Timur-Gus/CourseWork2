package com.course2.courseWork.controllers;

import com.course2.courseWork.myInterface.QuestionService;
import com.course2.courseWork.repository.Question;
import com.course2.courseWork.service.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Question> getAll() {
        return service.getAll();
    }
    @GetMapping("/add")
    public Question add(@RequestParam("question") String question, @RequestParam("answer") String answer){
        return service.add(question, answer);
    }
    @GetMapping("/remove")
    public Question remove(@RequestParam("question") String question, @RequestParam("answer")String answer){
        Question question1 = new Question(question, answer);
        return service.remove(question1);
    }
}
