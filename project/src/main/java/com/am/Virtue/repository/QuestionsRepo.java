package com.am.Virtue.repository;


import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Questions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionsRepo extends CrudRepository<Questions, Long> {
    public Questions findQuestionsById(Long id);

    public Questions findQuestionsByRating(Long rating);

    public List<Questions> findAllByOperationLanguage(OperationLanguage operationLanguage );

}
