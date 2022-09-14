package com.am.Virtue.resources;

import com.am.Virtue.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

public class AppointmentsResource {
    private long id;
    private Psychologists psychologists;
    private Account account;
    private Date AppoDate;
    //not optimal CHANGE to a string or an efficient time class
    private Date AppoTime;
    private LocalDateTime creationDate;
    private Status status;

    public AppointmentsResource toAppointmentsResource(Appointments appointments) {
        AppointmentsResource appointmentsResource = new AppointmentsResource();
        appointmentsResource.setAccount(appointments.getAccount());
        appointmentsResource.setPsychologists(appointments.getPsychologists());
        appointmentsResource.setAppoDate(appointments.getAppoDate());
        appointmentsResource.setAppoTime(appointments.getAppoTime());
        appointmentsResource.setCreationDate(appointments.getCreationDate());
        appointmentsResource.setStatus(appointments.getStatus());


        return appointmentsResource;
    }

    public Appointments toAppointments() {
        Appointments appointments = new Appointments();
        appointments.setAccount(this.account);
        appointments.setPsychologists(this.psychologists);
        appointments.setAppoDate(this.AppoDate);
        appointments.setAppoTime(this.AppoTime);
        appointments.setCreationDate(this.creationDate);
        appointments.setStatus(this.status);

        return appointments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Psychologists getPsychologists() {
        return psychologists;
    }

    public void setPsychologists(Psychologists psychologists) {
        this.psychologists = psychologists;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getAppoDate() {
        return AppoDate;
    }

    public void setAppoDate(Date appoDate) {
        AppoDate = appoDate;
    }

    public Date getAppoTime() {
        return AppoTime;
    }

    public void setAppoTime(Date appoTime) {
        AppoTime = appoTime;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
