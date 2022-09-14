package com.am.Virtue.repository;

import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.SystemMessage;
import com.am.Virtue.entities.SystemMessageCaption;
import org.springframework.data.repository.CrudRepository;

public interface SystemMessageCaptionRepo extends CrudRepository<SystemMessageCaption, Long> {
    public SystemMessageCaption findSystemMessageCaptionById(Long id);

    public SystemMessageCaption findSystemMessageCaptionBySystemMessageAndOperationLanguage
            (SystemMessage systemMessage, OperationLanguage operationLanguage);
}

