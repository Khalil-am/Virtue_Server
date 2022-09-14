package com.am.Virtue.repository;

import com.am.Virtue.entities.Status;
import com.am.Virtue.entities.OTP;
import org.springframework.data.repository.CrudRepository;

public interface OTPRepo extends CrudRepository<OTP, Long> {
    public OTP findOTPById(Long id);

    public OTP findOTPByMobileNoAndStatus(String mobile, Status status);

    public OTP findOTPByEmailAndStatus(String email, Status status);

    //new
    public OTP findOTPByEmailAndCode(String email, String code);

    public OTP findByEmail(String email);

    public OTP findOTPByCode(String code);

    public OTP save(OTP otp);
}