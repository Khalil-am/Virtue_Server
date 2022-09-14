package com.am.Virtue.repository;

import com.am.Virtue.entities.City;
import com.am.Virtue.entities.Forms;
import com.am.Virtue.entities.OperationLanguage;
import org.springframework.data.repository.CrudRepository;

public interface FormsRepo extends CrudRepository<Forms, Long> {
    public Forms findFormsById(long id);
}
