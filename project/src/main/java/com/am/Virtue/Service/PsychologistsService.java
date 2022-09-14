package com.am.Virtue.Service;

import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Status;

import java.util.List;

public interface PsychologistsService {
    public Psychologists findPsychologistsById(Long id);

    public Psychologists findPsychologistsByEmailAndStatus(String email, Status status);

    public Psychologists findPsychologistsByMobileAndStatus(String mobile, Status status);

    public Psychologists findPsychologistsByMobile(String mobile);

    public Psychologists save(Psychologists psychologists);

    public Psychologists login(Psychologists psychologists);

    public Psychologists changeAccountPassword(Psychologists psychologists, String newPassword);
    public Psychologists showAll();
    public List<Psychologists> findAllByOperationLanguage(OperationLanguage operationLanguage );


    interface StatusService {

        public Status findStatusByCode(String code);

        public Status createStatus(Status status);


    }
}