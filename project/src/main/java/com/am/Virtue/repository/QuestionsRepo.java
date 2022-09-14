package com.am.Virtue.repository;


import com.am.Virtue.entities.Questions;
import org.springframework.data.repository.CrudRepository;

public interface QuestionsRepo extends CrudRepository<Questions, Long> {
    public Questions findQuestionsById(Long id);

    public Questions findQuestionsByRating(Long rating);
}
