package com.am.Virtue.Service.impl;

import com.am.Virtue.Service.DiagnosisFormService;
import com.am.Virtue.entities.City;
import com.am.Virtue.entities.DiagnosisForm;
import com.am.Virtue.repository.CityRepo;
import com.am.Virtue.repository.DiagnosisFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DiagnosisFromImpl implements DiagnosisFormService {


    @Autowired
    private DiagnosisFormRepo diagnosisFormRepo;


    @Override
    public DiagnosisForm findDiagnosisFormById(long id) {
        return diagnosisFormRepo.findDiagnosisFormById(id);
    }

    @Override
    public DiagnosisForm save(DiagnosisForm diagnosisForm) {
        return diagnosisFormRepo.save(diagnosisForm);
    }
}
