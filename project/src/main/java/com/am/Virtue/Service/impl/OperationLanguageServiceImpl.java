package com.am.Virtue.Service.impl;


import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Status;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.OperationLanguageRepo;
import com.am.Virtue.repository.StatusRepo;
import com.am.Virtue.repository.SystemMessageRepo;
import com.am.Virtue.Service.OperationLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OperationLanguageServiceImpl implements OperationLanguageService {
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    SystemMessageRepo systemMessageRepo;
    @Autowired
    OperationLanguageRepo operationLanguageRepo;

    @Override
    public OperationLanguage findOperationLanguageByCode(String code) {
        return operationLanguageRepo.findOperationLanguageByCode(code);
    }

    @Override
    public OperationLanguage findOperationLanguageById(Long id) {
        return operationLanguageRepo.findOperationLanguageById(id);
    }

    @Override
    public OperationLanguage createOperationLanguage(OperationLanguage operationLanguage) {
        Status newLanguageStatus = statusRepo.findStatusById(11);
        if (newLanguageStatus == null)
            throw new ResourceException(HttpStatus.NOT_FOUND, systemMessageRepo.findSystemMessageById(1l).getText());
        operationLanguage.setStatusId(newLanguageStatus);//fix
        return operationLanguageRepo.save(operationLanguage);

    }
}
