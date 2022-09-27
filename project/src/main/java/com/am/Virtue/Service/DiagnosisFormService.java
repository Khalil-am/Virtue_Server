package com.am.Virtue.Service;

import com.am.Virtue.entities.City;
import com.am.Virtue.entities.DiagnosisForm;

public interface DiagnosisFormService {
    public DiagnosisForm findDiagnosisFormById(long id);

    public DiagnosisForm save(DiagnosisForm diagnosisform);


}
