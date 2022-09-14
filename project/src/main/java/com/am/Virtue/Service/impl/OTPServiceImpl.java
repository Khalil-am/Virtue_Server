package com.am.Virtue.Service.impl;


import com.am.Virtue.entities.Status;
import com.am.Virtue.repository.StatusRepo;
import com.am.Virtue.Service.OTPService;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.entities.OTP;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.OTPRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class OTPServiceImpl implements OTPService {

    @Autowired
    private OTPRepo otpRepo;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public OTP findOTPById(Long id) {
        return otpRepo.findOTPById(id);
    }

    @Override
    public OTP createOTP(OTP otp) {
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");

        if (activeStatus == null) {
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "status404", otp.getLanguageCode());
        }


        otp.setCreationDate(LocalDateTime.now());
        otp.setExpDate(LocalDateTime.now().plusMinutes(5));
        otp.setStatus(activeStatus);
        otp.setCode(Utils.randomNumber(4));
        otp.setDescription("Verification OTP");
        otp = otpRepo.save(otp);
        return otp;
    }

    @Override
    public OTP findOTPByMobileNoAndStatus(String mobile, Status status) {
        OTP otp = otpRepo.findOTPByMobileNoAndStatus(mobile, status);
        return otp;
    }

    @Override
    public OTP findOTPByEmailAndStatus(String email, Status status) {
        return otpRepo.findOTPByEmailAndStatus(email, status);
    }

    @Override
    public OTP findOTPByEmail(String email) {
        return otpRepo.findByEmail(email);
    }

    @Override
    public OTP findOTPByCode(String code) {
        OTP otp = otpRepo.findOTPByCode(code);
        return otp;
    }

    @Override
    public OTP findOTPByEmailAndAndCode(String email, String code) {
        OTP otp = otpRepo.findOTPByEmailAndCode(email, code);
        return otp;
    }

    @Override
    public OTP deactivateOTP(OTP otp) {
        OTP newOtp = otpRepo.findOTPById(otp.getId());
        if (newOtp == null)
            newOtp = otpRepo.findOTPByCode(otp.getCode());
        if (newOtp == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "otp404", otp.getLanguageCode());

        Status deactivate = statusRepo.findStatusByCode("INACTIVE");
        newOtp.setStatus(deactivate);

        return newOtp;
    }
}