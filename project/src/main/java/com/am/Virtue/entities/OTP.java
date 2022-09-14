package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Validation_OTP")
public class OTP implements Serializable {

    @Id
    @GenericGenerator(
            name = "OTPGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "OTP_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "OTPGenerator")
    @Column(name = "OTP_ID")
    private Long id;

    @Column(name = "OTP_Mobile_number")
    private String mobileNo;

    @Column(name = "OTP_Email")
    private String email;

    @Column(name = "OTP_CODE")
    private String code;

    @Column(name = "OTP_CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "OTP_EXPIRY_DATE")
    private LocalDateTime expDate;

    //@Column(name = "OTP_BIRTH_DATE")
    //private LocalDateTime birthDate;
    @OneToOne
    @JoinColumn(name = "OTP_sts_id", referencedColumnName = "sts_id")
    private Status status;

    @Column(name = "description")
    private String description;

    @Transient
    private String languageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
