package com.am.Virtue.repository;

import com.am.Virtue.entities.SystemMessage;
import org.springframework.data.repository.CrudRepository;

public interface SystemMessageRepo extends CrudRepository<SystemMessage, Long> {
    public SystemMessage findSystemMessageById(Long id);

    public SystemMessage findSystemMessageByCode(String code);
}

