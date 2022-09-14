package com.am.Virtue.repository;

import com.am.Virtue.entities.OperationLanguage;
import org.springframework.data.repository.CrudRepository;

public interface OperationLanguageRepo extends CrudRepository<OperationLanguage, Long> {
    public OperationLanguage findOperationLanguageById(long id);

    public OperationLanguage findOperationLanguageByCode(String code);

}
