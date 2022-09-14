package com.am.Virtue.repository;


import com.am.Virtue.entities.StatusCaption;
import org.springframework.data.repository.CrudRepository;

public interface StatusCaptionRepo extends CrudRepository<StatusCaption, Long> {
    public StatusCaption findStatusCaptionById(long id);

}
