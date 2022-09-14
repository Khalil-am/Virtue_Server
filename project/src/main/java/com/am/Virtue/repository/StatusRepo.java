package com.am.Virtue.repository;


import com.am.Virtue.entities.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepo extends CrudRepository<Status,Long> {
    public Status findStatusById(long id);
    public Status findByCode(String code);
    public Status findStatusByCode(String code);
    public Status save(Status status);

}
