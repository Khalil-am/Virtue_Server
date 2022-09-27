package com.am.Virtue.Service.impl;

import com.am.Virtue.Service.QuestionsSerivce;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Questions;
import com.am.Virtue.repository.QuestionsRepo;
import com.am.Virtue.repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionsServiceImpl implements QuestionsSerivce {

    @Autowired
    QuestionsRepo questionsRepo;

    public Questions findQuestionsById(Long id) {
        return questionsRepo.findQuestionsById(id);
    }

    public Questions findQuestionsByRating(Long rating) {
        return questionsRepo.findQuestionsByRating(rating);
    }
    public List<Questions> findAllByOperationLanguage(OperationLanguage operationLanguage ){
            List<Questions> questions=questionsRepo.findAllByOperationLanguage(operationLanguage);
            return questions;
        }
    }
