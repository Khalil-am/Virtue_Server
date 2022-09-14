package com.am.Virtue.Service;


import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Status;
import com.am.Virtue.entities.OTP;

public interface OTPService  {
    public OTP findOTPById(Long id);
    public OTP deactivateOTP(OTP otp);
    public OTP createOTP (OTP  otp);
    public OTP findOTPByMobileNoAndStatus(String mobile, Status status);
    public OTP findOTPByEmailAndStatus(String email, Status status);
    public OTP findOTPByEmail(String email);
    public OTP findOTPByCode(String code);
    public OTP findOTPByEmailAndAndCode(String email,String code);

}
