package com.am.Virtue.resources;

import com.am.Virtue.entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentsResource {
    private String id;
    private String psychologists;
    private String account;
    private String date;
    //not optimal CHANGE to a string or an efficient time class
    private String time;
    private String creationDate;
    private String status;
    private String type;

    //TODO fix
    public static AppointmentsResource toAppointmentsResource(Appointments appointments) {
        AppointmentsResource appointmentsResource = new AppointmentsResource();
        appointmentsResource.setId(String.valueOf(appointments.getId()));
         appointmentsResource.setAccount(String.valueOf(appointments.getAccount().getId()));
          appointmentsResource.setPsychologists(String.valueOf(appointments.getPsychologists().getId()));
        //bad code
        appointmentsResource.setType(String.valueOf(Long.parseLong(String.valueOf(appointments.getType()))));
        appointmentsResource.setDate(appointments.getAppoDate());
        appointmentsResource.setTime(appointments.getAppoTime());
        appointmentsResource.setCreationDate(appointments.getCreationDate().toString());
        appointmentsResource.setStatus(appointments.getStatus().getId().toString());


        return appointmentsResource;
    }

    public Appointments toAppointments() {
        Appointments appointments = new Appointments();
     //   appointments.setId(Long.parseLong(id));
        //appointments.setAccount(account);
        if (account != null) {
            Account account1 = new Account();
            account1.setId(Long.parseLong(account));
        }
        //  appointments.setPsychologists(this.psychologists);
        if (psychologists != null) {
            Psychologists psychologists1 = new Psychologists();
            psychologists1.setId(Long.parseLong(psychologists));
        }
        appointments.setType(Long.parseLong(this.type));
        appointments.setAppoDate(this.date);
        appointments.setAppoTime(this.time);
        appointments.setCreationDate(LocalDateTime.now());
        appointments.setStatus(appointments.getStatus());//TODO fix

        return appointments;
    }

    public Appointments toAppointmentsId() {
        Appointments appointments = new Appointments();
        appointments.setId(Long.parseLong(String.valueOf(this.id)));
        return appointments;
    }

    public  static List<AppointmentsResource> toAppointmentsResource(List<Appointments> AppointmentsList) {
        List<AppointmentsResource> appointmentsResourceList = new ArrayList<>();
        AppointmentsList.forEach(appointments -> {
            AppointmentsResource appointmentsResource = toAppointmentsResource(appointments);
            appointmentsResourceList.add(appointmentsResource);
        });
        return appointmentsResourceList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsychologists() {
        return psychologists;
    }

    public void setPsychologists(String psychologists) {
        this.psychologists = psychologists;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
