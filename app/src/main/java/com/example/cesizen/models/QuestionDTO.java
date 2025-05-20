package com.example.cesizen.models;

import java.util.List;

public class QuestionDTO {
    private Long id;
    private String question;
    private String rule;
    private Integer number_expected_answers;
   private List<AnswerDTO> listOfAnswers;

    public QuestionDTO(Long id, String question, String rule, Integer number_expected_answers, List<AnswerDTO> listOfAnswers) {
        this.id = id;
        this.question = question;
        this.rule = rule;
        this.number_expected_answers = number_expected_answers;
        this.listOfAnswers = listOfAnswers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getNumber_expected_answers() {
        return number_expected_answers;
    }

    public void setNumber_expected_answers(Integer number_expected_answers) {
        this.number_expected_answers = number_expected_answers;
    }

    public List<AnswerDTO> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<AnswerDTO> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }
}
