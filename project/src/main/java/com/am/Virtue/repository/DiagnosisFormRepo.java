package com.am.Virtue.repository;

import com.am.Virtue.entities.DiagnosisForm;
import org.springframework.data.repository.CrudRepository;

public interface DiagnosisFormRepo extends CrudRepository<DiagnosisForm, Long> {
    public DiagnosisForm findDiagnosisFormById(long id);

    public DiagnosisForm save(DiagnosisForm diagnosisform);

}