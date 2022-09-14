package com.am.Virtue.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Account")
public class Account {
    @GenericGenerator(
            name = "Acc_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "ACC_SEQ", value = "ACCOUNT_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "Acc_Seq")
    @Column(name = "ACC_ID")
    private Long id;
    @Column(name = "ACC_FIRST_NAME")
    private String firstName;

    @Column(name = "ACC_LAST_NAME")
    private String lastName;
    @Column(name = "ACC_EMAIL")
    private String email;
    @Column(name = "ACC_MOBILE")
    private String mobileNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "ACC_BIRTHDATE")
    private Date Birthdate;
    @OneToOne
    @JoinColumn(name = "ACC_Forms_Id",referencedColumnName = "Forms_Id")
    private Forms forms;
    @Column(name = "ACC_PASSWORD")
    private String password;
    @Column(name = "ACC_CREATION_DATE")
    private LocalDateTime creationDate;
    @OneToOne //?
    @JoinColumn(name = "ACC_Cit_id", referencedColumnName = "Cit_ID")
    private City city;
    @OneToOne
    @JoinColumn(name = "ACC_District_Id", referencedColumnName = "dst_ID")
    private District district;
    @OneToOne
    @JoinColumn(name = "ACC_Gender_ID", referencedColumnName = "Gender_Id")
    private Gender gender;
    @OneToOne
    @JoinColumn(name = "ACC_Sts_Id", referencedColumnName = "Sts_Id")
    private Status status;
    @OneToOne
    @JoinColumn(name = "ACC_Lang_Id")
    private OperationLanguage operationLanguage;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(Date birthdate) {
        Birthdate = birthdate;
    }

    public OperationLanguage getOperationLanguage() {
        return operationLanguage;
    }

    public void setOperationLanguage(OperationLanguage operationLanguage) {
        this.operationLanguage = operationLanguage;
    }
}

