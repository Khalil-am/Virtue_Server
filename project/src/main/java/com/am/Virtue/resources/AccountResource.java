package com.am.Virtue.resources;

import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class AccountResource {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String password;
    private String confirmPassword;
    private Long cityId;
    private Long districtId;
    private Long genderId;
    private LocalDateTime creationDate;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthdate;
    private String otp;
    private Long operationLanguageId;
    private String newPassword;
    private String token;

    public Long getOperationLanguageId() {
        return operationLanguageId;
    }

    public void setOperationLanguageId(Long operationLanguageId) {
        this.operationLanguageId = operationLanguageId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public static AccountResource toAccountResource(Account account) {
        AccountResource accountResource = new AccountResource();
        accountResource.setId(account.getId());
        accountResource.setFirstName(account.getFirstName());
        accountResource.setLastName(account.getLastName());
        accountResource.setEmail(account.getEmail());
        accountResource.setMobileNumber(account.getMobileNumber());
        accountResource.setPassword(account.getPassword());
        //  accountResource.setGenderId(account.getGender());
        accountResource.setBirthdate(accountResource.getBirthdate());
        accountResource.setCityId(account.getCity() != null ? account.getCity().getId() : null);
        accountResource.setCreationDate(account.getCreationDate());

        return accountResource;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setId(this.id);
        account.setFirstName(this.firstName);
        account.setLastName(this.lastName);
        account.setEmail(this.email);
        account.setMobileNumber(this.mobileNumber);
        account.setPassword(this.password);
        account.setPassword(this.password);
        account.setCreationDate(this.creationDate);
        account.setBirthdate(this.birthdate);
        if (genderId != null) {
            Gender gender = new Gender();
            gender.setId(this.genderId);
            account.setGender(gender);
        }
        return account;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
