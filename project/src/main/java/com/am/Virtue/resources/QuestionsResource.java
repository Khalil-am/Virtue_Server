package com.am.Virtue.resources;

import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Questions;
import com.am.Virtue.entities.Status;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

public class QuestionsResource {
    private Long id;
    private Long rating;
    private String description;
    private Status status;

    public Questions toQuestions(QuestionsResource questionsResource) {
        Questions questions = new Questions();
        questions.setId(questionsResource.getId());
        questions.setDescription(questionsResource.getDescription());
        questions.setRating(questionsResource.getRating());
        questions.setStatus(questionsResource.getStatus());
        return questions;
    }

    public  List<QuestionsResource> toResourceList(List<Questions> QuestionsList) {
        List<QuestionsResource> cityCaptionResourceList = new ArrayList<>();
        QuestionsList.forEach(questions -> {
            QuestionsResource questionsResource = toResource(questions);
            cityCaptionResourceList.add(questionsResource);
        });
        return cityCaptionResourceList;
    }

    public QuestionsResource toResource(Questions questions) {
        QuestionsResource questionsResource = new QuestionsResource();
        questionsResource.setId(questions.getId());
        questionsResource.setDescription(questions.getDescription());
        questionsResource.setRating(questions.getRating());
        questionsResource.setStatus(questions.getStatus());
        return questionsResource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
