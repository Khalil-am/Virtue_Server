package com.am.Virtue.Service.impl;


import com.am.Virtue.entities.SystemMessage;
import com.am.Virtue.entities.SystemMessageCaption;
import com.am.Virtue.Service.SystemMessageCaptionService;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.repository.SystemMessageCaptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SystemMessageCaptionServiceImpl implements SystemMessageCaptionService {

    @Autowired
    private SystemMessageCaptionRepo systemMessageCaptionRepo;

    @Override
    public SystemMessageCaption findMessageCaptionByMessageAndLanguage(SystemMessage systemMessage, OperationLanguage operationLanguage) {
        return systemMessageCaptionRepo.findSystemMessageCaptionBySystemMessageAndOperationLanguage(systemMessage, operationLanguage);
    }
}
