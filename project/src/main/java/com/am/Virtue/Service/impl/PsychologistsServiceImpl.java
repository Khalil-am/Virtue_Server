package com.am.Virtue.Service.impl;


import com.am.Virtue.Service.PsychologistsService;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Status;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.OperationLanguageRepo;
import com.am.Virtue.repository.PsychologistsRepo;
import com.am.Virtue.repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PsychologistsServiceImpl implements PsychologistsService {

    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private PsychologistsRepo psychologistsRepo;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private OperationLanguageRepo operationLanguageRepo;

    @Override
    public Psychologists findPsychologistsById(Long id) {
        Psychologists psychologists = psychologistsRepo.findPsychologistsById(id);
        return psychologists;
    }


    @Override
    public Psychologists findPsychologistsByEmailAndStatus(String email, Status status) {
        return psychologistsRepo.findPsychologistsByEmailAndStatus(email, status);
    }

    @Override
    public Psychologists findPsychologistsByMobileAndStatus(String mobile, Status status) {
        Psychologists psychologists = psychologistsRepo.findPsychologistsByMobileNumberAndStatus(mobile, status);
        return psychologists;
    }


    @Override
    public Psychologists findPsychologistsByMobile(String mobile) {
        Psychologists psychologists = psychologistsRepo.findPsychologistsByMobileNumber(mobile);
        return psychologists;
    }

    @Override
    public Psychologists login(Psychologists psychologists) {
        OperationLanguage operationLanguage = operationLanguageRepo.findOperationLanguageByCode(psychologists.getOperationLanguage().getCode());
        if (operationLanguage == null) {
            operationLanguage = operationLanguageRepo.findOperationLanguageByCode("en");
        }
        Status active = statusRepo.findStatusByCode("ACTIVE");
        if (active == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", operationLanguage.getCode());
        }
        Status Inactive = statusRepo.findStatusByCode("INACTIVE");
        if (Inactive == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", operationLanguage.getCode());
        }
        // User credential
        String email = psychologists.getEmail();
//        String password = account.getPassword();
        //String password = dcCripto.encrypt(account.getPassword());

        // Find Account by UserName and status Active & InActive
        Psychologists findUser = psychologistsRepo.findPsychologistsByEmailAndStatus(email, active);
        if (findUser == null) {
            if (psychologistsRepo.findPsychologistsByEmailAndStatus(email, Inactive) != null) {
                throw new ResourceException(applicationContext, HttpStatus.FORBIDDEN, "Account423", operationLanguage.getCode());
            }
            throw new ResourceException(applicationContext, HttpStatus.FORBIDDEN, "Email404", operationLanguage.getCode());
        }
        return findUser;
    }

    @Override
    public Psychologists changeAccountPassword(Psychologists psychologists, String newPassword) {
        Status status = statusRepo.findStatusByCode("ACTIVE");
        Psychologists psychologists1 = psychologistsRepo.findPsychologistsByMobileNumberAndStatus(psychologists.getMobileNumber(), status);
        psychologists1.setPassword(newPassword);
        return psychologists1;
    }

    @Override
    public Psychologists showAll() {
        return null;
    }

    @Override
    public Psychologists save(Psychologists psychologists) {
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", "EN");

        Psychologists currnetAccount = null;
        currnetAccount = psychologistsRepo.findPsychologistsByMobileNumberAndStatus(psychologists.getMobileNumber(), activeStatus);
        if (currnetAccount != null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "acc302", "EN");

        currnetAccount = psychologistsRepo.findPsychologistsByEmailAndStatus(psychologists.getEmail(), activeStatus);
        if (currnetAccount != null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "acc302", "EN");

        psychologists.setStatus(activeStatus);
        psychologists.setCreationDate(LocalDateTime.now());
        return psychologistsRepo.save(psychologists);
    }

    @Override
    public List<Psychologists> findAllByOperationLanguage(OperationLanguage operationLanguage) {
        List<Psychologists> psychologists=psychologistsRepo.findAllByOperationLanguage(operationLanguage);
        return psychologists;
    }
}