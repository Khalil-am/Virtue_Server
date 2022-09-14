package com.am.Virtue.Service.impl;

import com.am.Virtue.Service.AppointmentsService;
import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.Appointments;
import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Status;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class AppointmentsServiceImpl implements AppointmentsService {
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private AppointmentsRepo appointmentsRepo;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private OperationLanguageRepo operationLanguageRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PsychologistsRepo psychologistsRepo;

    @Override
    public Appointments findAppointmentsByAccId(Long id) {
        Account account = accountRepo.findAccountById(id);
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", account.getLangCode());


        Appointments appointments = appointmentsRepo.findAppointmentsByAccount_Id(account.getId());

        return appointments;
    }

    public Appointments findPsychologists_AppointmentsById(Long id) {
        Psychologists psychologists = psychologistsRepo.findPsychologistsById(id);
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", psychologists.getLangCode());


        Appointments appointments = appointmentsRepo.findAppointmentsByPsychologistsId(psychologists.getId());

        return appointments;
    }

    //test
    public Appointments save(Appointments appointments) {
        Account currentAccount = accountRepo.findAccountById(appointments.getAccount().getId());
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", currentAccount.getLangCode());

        if (currentAccount != null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "acc302", currentAccount.getLangCode());


        if (appointments.getAppoDate() == null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "Appo_404_Date", currentAccount.getLangCode());
        if (appointments.getAppoTime() == null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "Appo_404_time", currentAccount.getLangCode());
        //appointments.setAccount(currentAccount);//fix
        appointments.setStatus(activeStatus);
        appointments.setCreationDate(LocalDateTime.now());
        Psychologists psychologists = psychologistsRepo.findPsychologistsById(appointments.getPsychologists().getId());
        if (psychologists == null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "acc404", currentAccount.getLangCode());
        appointments.setPsychologists(psychologists);
        return appointmentsRepo.save(appointments);
    }

}
