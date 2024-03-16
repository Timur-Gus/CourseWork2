package com.course2.courseWork.constants;

import model.Question;

import java.util.ArrayList;
import java.util.List;

public class ConstantsQuestionTest {
    public static final String QUESTION_1 = "Вопрос 1";
    public static final String QUESTION_2 = "Вопрос 2";
    public static final String QUESTION_3 = "Вопрос 3";
    public static final String ANSWER_1 = "Ответ 1";
    public static final String ANSWER_2= "Ответ 2";
    public static final String ANSWER_3 = "Ответ 3";

    public static final Question QUESTION_AND_ANSWER_1 = new Question(QUESTION_1, ANSWER_1);
    public static final Question QUESTION_AND_ANSWER_2 = new Question(QUESTION_2, ANSWER_2);
    public static final Question QUESTION_AND_ANSWER_3 = new Question(QUESTION_3, ANSWER_3);
    public static final Question MATH_QUESTION_AND_ANSWER = new Question("Question_Math", "Answer_Math");
    public static final List<Question> QUESTIONS = new ArrayList<>(List.of(
            QUESTION_AND_ANSWER_1, QUESTION_AND_ANSWER_2, QUESTION_AND_ANSWER_3
    ));
    public static final List<Question> MATH_QUESTIONS = new ArrayList<>(List.of(
            MATH_QUESTION_AND_ANSWER,
            new Question("Question_Math_1", "Answer_Math_1")
    ));

    public static final List<Question> QUESTIONS1 = new ArrayList<>(List.of(
            QUESTION_AND_ANSWER_2, QUESTION_AND_ANSWER_3
    ));

}
