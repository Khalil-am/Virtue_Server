package com.am.Virtue.Service;

import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Questions;

import java.util.List;

public interface QuestionsSerivce {

    public Questions findQuestionsById(Long id);
    public Questions findQuestionsByRating(Long rating);

    public List<Questions> findAllByOperationLanguage(OperationLanguage operationLanguage );
}
