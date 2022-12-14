package com.am.Virtue.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Psychologists_acc")
public class Psychologists {
    @Id
    @GenericGenerator(
            name = "Psychologists",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "OTP_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "Psychologists_acc")
    @Column(name = "PSY_ACC_ID")
    private Long id;
    @Column(name = "PSY_ACC_FIRST_NAME")
    private String firstName;

    @Column(name = "PSY_ACC_LAST_NAME")
    private String lastName;
    @Column(name = "PSY_ACC_EMAIL")
    private String email;
    @Column(name = "PSY_ACC_MOBILE")
    private String mobileNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "PSY_ACC_BIRTHDATE")
    private Date Birthdate;
    @Column(name = "PSY_ACC_PASSWORD")
    private String password;
    @Column(name = "PSY_ACC_CREATION_DATE")
    private LocalDateTime creationDate;
    @OneToOne //?
    @JoinColumn(name = "PSY_ACC_Cit_id", referencedColumnName = "Cit_ID")
    private City city;
    @OneToOne
    @JoinColumn(name = "PSY_ACC_Gender_ID", referencedColumnName = "Gender_Id")
    private Gender gender;
    @OneToOne
    @JoinColumn(name = "PSY_ACC_Sts_Id", referencedColumnName = "Sts_Id")
    private Status status;
    @OneToOne
    @JoinColumn(name = "PSY_ACC_Lang_Id")
    private OperationLanguage operationLanguage;
    @OneToOne
    @JoinColumn(name = "PSY_CUSTOMER", referencedColumnName = "ACC_ID")
    private Account Account;
    @Transient
    private String langCode;

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
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
        return Birthdate;
    }

    public void setBirthdate(Date birthdate) {
        Birthdate = birthdate;
    }

    public String getPassword() {
        return password;
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

    public void setCity(City city) {
        this.city = city;
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
}
