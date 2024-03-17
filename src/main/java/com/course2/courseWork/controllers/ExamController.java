package com.course2.courseWork.controllers;

import com.course2.courseWork.service.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {
    public final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public String getQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount).toString();
    }
}
