package com.am.Virtue.resources;


import com.am.Virtue.entities.Status;
import com.am.Virtue.entities.OTP;

import java.time.LocalDateTime;

public class OTPResource {
    private Long id;

    private String mobileNo;
    private String email;
    private String code;
    //    private LocalDateTime birthDate;
    private LocalDateTime creationDate;

    private LocalDateTime expDate;
    private Status status;

    private String description;
    private String languageCode;

    public static OTPResource toResource(OTP otp) {
        OTPResource otpResource = new OTPResource();
        otpResource.setId(otp.getId());
        otpResource.setEmail(otp.getEmail());
        otpResource.setCode(otp.getCode());
        otpResource.setDescription(otp.getDescription());
        otpResource.setLanguageCode(otp.getLanguageCode());
        otpResource.setMobileNo(otp.getMobileNo());
        otpResource.setExpDate(otp.getExpDate());
        otpResource.setCreationDate(otp.getCreationDate());
        otpResource.setStatus(otp.getStatus());
//        otpResource.setBirthDate(otp.getBirthDate());
        return otpResource;
    }

    public static OTP toResource(OTPResource otpResource) {
        OTP otp = new OTP();
        otp.setId(otpResource.getId());
        otp.setEmail(otpResource.getEmail());
        otp.setCode(otpResource.getCode());
        otp.setDescription(otpResource.getDescription());
        otp.setLanguageCode(otpResource.getLanguageCode());
        otp.setMobileNo(otpResource.getMobileNo());
        otp.setExpDate(otpResource.getExpDate());
        otp.setCreationDate(otpResource.getCreationDate());
        otp.setStatus(otpResource.getStatus());
//        otp.setBirthDate(otpResource.getBirthDate());
        return otp;
    }

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


