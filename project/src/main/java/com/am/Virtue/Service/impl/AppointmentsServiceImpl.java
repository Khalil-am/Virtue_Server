package com.am.Virtue.Service.impl;

import com.am.Virtue.Service.AppointmentsService;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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
        OperationLanguage operationLanguage = operationLanguageRepo.findOperationLanguageByCode("EN");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", account.getLangCode());

        if (account == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Acc404", operationLanguage.getCode());

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

    public Appointments findAppointmentsByDateAndTime(String date, String time) {
        Appointments appointments = appointmentsRepo.findAppointmentsByAppoDateAndAppoTime(date, time);
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", "fix status");

        if (appointments != null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "acc302", "Exists");
        return appointments;

    }


    //test
    public Appointments save(Appointments appointments) {
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", "Status");
//TODO add another time and date testing layer
        appointments.setStatus(activeStatus);
        appointments.setCreationDate(LocalDateTime.now());
        return appointmentsRepo.save(appointments);

    }

    @Override
    public Appointments deactivateAppointmentById(Long id) {
        {
            Status activeStatus = statusRepo.findStatusByCode("ACTIVE");

            if (activeStatus == null)
                throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", "Status");
            Status inactiveStatus = statusRepo.findStatusByCode("INACTIVE");

            Appointments appointments = appointmentsRepo.findAppointmentsByIdAndStatus(id, activeStatus);
            appointments.setStatus(inactiveStatus);
            return appointmentsRepo.save(appointments);
        }
    }

    @Override
    public List<Appointments> findAllByOperationLanguage(OperationLanguage operationLanguage) {
        List<Appointments> appointments = appointmentsRepo.findAllByOperationLanguage(operationLanguage);
        return appointments;
    }

    @Override
    public List<Appointments> findAllByAccountId(Long id) {
        List<Appointments> appointments = appointmentsRepo.findAllByAccountId(id);
        return appointments;
    }
}



