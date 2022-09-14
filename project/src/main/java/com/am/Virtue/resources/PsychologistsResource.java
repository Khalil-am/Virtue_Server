package com.am.Virtue.resources;

import com.am.Virtue.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PsychologistsResource {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;


    private Date birthdate;

    private String password;
    private String confirmPassword;

    private LocalDateTime creationDate;

    private City city;

    private Gender gender;

    private Status status;

    private OperationLanguage operationLanguage;

    private Account Account;

    private String langCode;

    public static PsychologistsResource toPsychologistsResource(Psychologists psychologists) {
        PsychologistsResource psychologistsResource = new PsychologistsResource();
        psychologistsResource.setFirstName(psychologists.getFirstName());
        psychologistsResource.setLastName(psychologists.getLastName());
        psychologistsResource.setEmail(psychologists.getEmail());
        psychologistsResource.setMobileNumber(psychologists.getMobileNumber());
        psychologistsResource.setPassword(psychologists.getPassword());
        //  accountResource.setGenderId(account.getGender());
        psychologistsResource.setBirthdate(psychologists.getBirthdate());
        psychologistsResource.setAccount(psychologists.getAccount());
        psychologistsResource.setCity(psychologists.getCity() != null ? psychologists.getCity().getId() : null); //fix
        psychologistsResource.setCreationDate(psychologists.getCreationDate());

        return psychologistsResource;
    }

    public static List<PsychologistsResource> toResource(List<Psychologists> PsychologistsList) {
        List<PsychologistsResource> cityCaptionResourceList = new ArrayList<>();
        PsychologistsList.forEach(psychologists -> {
            PsychologistsResource psychologistsResource = toPsychologistsResource(psychologists);
            cityCaptionResourceList.add(psychologistsResource);
        });
        return cityCaptionResourceList;
    }

    public Psychologists toPsychologists() {
        Psychologists psychologists = new Psychologists();
        psychologists.setFirstName(this.firstName);
        psychologists.setLastName(this.lastName);
        psychologists.setEmail(this.email);
        psychologists.setMobileNumber(this.mobileNumber);
        psychologists.setPassword(this.password);
        psychologists.setPassword(this.password);
        psychologists.setCreationDate(this.creationDate);
        psychologists.setAccount(this.Account);
        psychologists.setBirthdate(this.birthdate);
        if (gender.getId() != null) {
            Gender gender = new Gender();
            gender.setId(this.gender.getId());
            psychologists.setGender(gender);
        }
        return psychologists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = getCity();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OperationLanguage getOperationLanguage() {
        return operationLanguage;
    }

    public void setOperationLanguage(OperationLanguage operationLanguage) {
        this.operationLanguage = operationLanguage;
    }

    public com.am.Virtue.entities.Account getAccount() {
        return Account;
    }

    public void setAccount(com.am.Virtue.entities.Account account) {
        Account = account;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }
}
