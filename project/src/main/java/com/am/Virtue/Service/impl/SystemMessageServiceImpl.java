package com.am.Virtue.Service.impl;


import com.am.Virtue.entities.Status;
import com.am.Virtue.entities.SystemMessage;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.OperationLanguageRepo;
import com.am.Virtue.repository.StatusRepo;
import com.am.Virtue.Service.SystemMessageService;
import com.am.Virtue.repository.SystemMessageRepo;
import com.am.Virtue.entities.OperationLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SystemMessageServiceImpl implements SystemMessageService {
    @Autowired
    SystemMessageRepo messageRepo;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    OperationLanguageRepo operationLanguageRepo;


    @Override
    public SystemMessage findSystemMessageByCode(String code) {
        SystemMessage message = messageRepo.findSystemMessageByCode(code);
        return message;
    }

    @Override
    public SystemMessage createSystemMessage(SystemMessage systemMessage) {
        Status newSystemMessageStatus = statusRepo.findStatusByCode("ACTIVE");

        if (newSystemMessageStatus == null)
            throw new ResourceException(HttpStatus.NOT_FOUND, messageRepo.findSystemMessageById(1l).getText());
        OperationLanguage newSystemMessageOperationLanguage = operationLanguageRepo.findOperationLanguageById(11);
//        if(newSystemMessageOperationLanguage ==null)
//            throw new ResourceException(HttpStatus.NOT_FOUND, messageRepo.findSystemMessageById(1l).getText());
        // systemMessage.setOperationLanguage(newSystemMessageOperationLanguage);
        systemMessage.setStatus(newSystemMessageStatus);

        SystemMessage currentSystemMessage = messageRepo.findSystemMessageByCode(systemMessage.getCode());
        if (currentSystemMessage != null) {
            currentSystemMessage.setText(systemMessage.getText());
            currentSystemMessage.setDescription(systemMessage.getDescription());
            return currentSystemMessage;
        }

        return messageRepo.save(systemMessage);

    }
}
