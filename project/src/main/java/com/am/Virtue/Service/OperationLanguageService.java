package com.am.Virtue.Service;

import com.am.Virtue.entities.OperationLanguage;

public interface OperationLanguageService {
    public OperationLanguage findOperationLanguageByCode(String code);

    public OperationLanguage findOperationLanguageById(Long id);

    public OperationLanguage createOperationLanguage(OperationLanguage operationLanguage);

}
