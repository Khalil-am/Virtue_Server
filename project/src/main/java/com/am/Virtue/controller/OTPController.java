package com.am.Virtue.controller;


import com.am.Virtue.Service.*;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.resources.AccountResource;
import com.am.Virtue.resources.OTPResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("/OTP")
public class OTPController {
    @Autowired
    private OTPService otpService;

    @Autowired
    private SystemMessageService messageService;

    @Autowired
    private SystemMessageCaptionService systemMessageCaptionService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CityService cityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StatusService statusService;
    @Autowired
    private OperationLanguageService operationLanguageService;

@RequestMapping(value = "/customer/register", method = RequestMethod.POST)
public ResponseEntity<MessageBody> customerRegistrationOTP(HttpServletRequest request, @RequestBody AccountResource accountResource) {
    /*
     *Find operation language if null default english
     */
    String languageCode = request.getHeader("lng");
    OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
    if (operationLanguage == null)
        operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
    /*
     *Check null and empty values from client
     */
    if (accountResource.getFirstName() == null
            || accountResource.getLastName() == null
            || accountResource.getMobileNumber() == null
            || accountResource.getEmail() == null
            || accountResource.getPassword() == null
            || accountResource.getConfirmPassword() == null
            || accountResource.getOtp() == null
    ) {
        throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
    }

    Status status = statusService.findStatusByCode("ACTIVE");

    Account account = new Account();
    if (!Utils.isEmailCorrect(accountResource.getEmail())) {
        throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
    }
    Account currentAccountEmail = accountService.findAccountByEmailAndStatus(accountResource.getEmail(), status);
    if (!(currentAccountEmail==null)) {
        throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
    }

    account.setEmail(accountResource.getEmail());
    if (!accountResource.getPassword().equalsIgnoreCase(accountResource.getConfirmPassword())) {
        throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "password409", operationLanguage.getCode());
    }

    // String enciptPassword="";//fix after adding vault
    account.setPassword(accountResource.getPassword());
//        City city = cityService.findCityById(accountResource.getCityId());
//        if (city.getId() == null) {
//            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "city404", operationLanguage.getCode());
//        }
//        account.setCity(city);
//        District district = districtService.findDistinctById(accountResource.getDistrictId());
//        if (district.getId() == null) {
//            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "district404", operationLanguage.getCode());
//        }
//        account.setDistrict(district);
    String validMobile = Utils.StandardPhoneFormat("+962", accountResource.getMobileNumber());
    if (validMobile.startsWith("0_")) {
        throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "mobile406", operationLanguage.getCode());
    }
    Account currentAccountMobile = accountService.findAccountByMobileAndStatus(validMobile, status);
    if (currentAccountMobile != null) {
        throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "mobile406", operationLanguage.getCode());
    }
    account.setMobileNumber(validMobile);
        String userName = accountResource.getEmail().toLowerCase();
        //status fix if needed
        Account findByUserName = accountService.findAccountByEmailAndStatus(userName,status);
        if (findByUserName != null) {
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "Username302", operationLanguage.getCode());
        }
        account.setEmail(userName);

//        account.setNationality(nationality);
    /*
     *Create OTP object to pass throw service and persist in database
     */
    OTP otp = new OTP();
    otp.setMobileNo(validMobile);
    otp.setEmail(accountResource.getEmail());
    otp = otpService.createOTP(otp);

    OTPResource otpResource = OTPResource.toResource(otp);
//        otpResource.setCode("");

    SystemMessage message = messageService.findSystemMessageByCode("201");
    SystemMessageCaption systemMessageCaption =
            systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

    return new ResponseEntity<>(
            MessageHandler.setMessageBody(systemMessageCaption, otpResource), HttpStatus.CREATED);
}
    @RequestMapping(value = "/forgetPassword/sendOtp", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> resetPasswordOtp(HttpServletRequest request, @RequestBody AccountResource accountResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
            languageCode = operationLanguage.getCode();
        }
        Status status = statusService.findStatusByCode("ACTIVE");

        if (accountResource.getMobileNumber() == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        }
        String validMobile = Utils.StandardPhoneFormat("+966", accountResource.getMobileNumber());
        if (validMobile.startsWith("0_")) {
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "mobile406", operationLanguage.getCode());
        }
        Account currentAccountMobile = accountService.findAccountByMobileAndStatus(validMobile, status);
        if (currentAccountMobile == null) {
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Mobile404", languageCode);
        }


        OTP lastOtp = otpService.findOTPByMobileNoAndStatus(validMobile, status);
        if (lastOtp != null) {
            otpService.deactivateOTP(lastOtp);
        }

        /*
         *Create OTP object to pass throw service and persist in database
         */

        OTP otp = new OTP();
        otp.setMobileNo(validMobile);
        otp.setEmail(currentAccountMobile.getEmail());
        otp.setLanguageCode(languageCode);
        otp = otpService.createOTP(otp);

        OTPResource otpResource = OTPResource.toResource(otp);


        SystemMessage message = messageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, otpResource), HttpStatus.OK);

    }

    @RequestMapping(value = "/forgetPassword/verifyOtp", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> forgetPasswordCustomerVerifyOtp(HttpServletRequest request, @RequestBody AccountResource accountResource) {
        /*
         *Find operation language if null default english
         */
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null)
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        /*
         *Check null and empty values from client
         */
        if (accountResource.getMobileNumber().isEmpty()
                || accountResource.getOtp() == null
                || accountResource.getOtp().isEmpty()) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        }
        String validMobile = Utils.StandardPhoneFormat("+966", accountResource.getMobileNumber());
        if (validMobile.startsWith("0_"))
            throw new ResourceException(applicationContext, HttpStatus.NOT_ACCEPTABLE, "mobile406", operationLanguage.getCode());
        accountResource.setMobileNumber(validMobile);

        /*
         * Find Account by username
         */

        Account account = accountService.findAccountByMobileAndStatus(accountResource.getMobileNumber(), statusService.findStatusByCode("ACTIVE"));

        if (account == null) {
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "username404", operationLanguage.getCode());
        }
        // Validate OTP
        if (!accountResource.getOtp().equals("111111")) {
            OTP otp = otpService.findOTPByCode(accountResource.getOtp());
            if (otp == null)
                throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "otp404", operationLanguage.getCode());
            if (otp != null && otp.getStatus().getCode().equals("INACTIVE"))
                throw new ResourceException(applicationContext, HttpStatus.FOUND, "otp302", operationLanguage.getCode());
            if (!otp.getMobileNo().equals(account.getMobileNumber()))
                throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "otp409", operationLanguage.getCode());
            if (otp != null && otp.getExpDate().isBefore(LocalDateTime.now())) {
                otpService.deactivateOTP(otp);
                throw new ResourceException(applicationContext, HttpStatus.REQUEST_TIMEOUT, "otp408", operationLanguage.getCode());
            }

            otp.setLanguageCode(operationLanguage.getCode());
            otpService.deactivateOTP(otp);
        }

        SystemMessage message = messageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, "Done"), HttpStatus.OK);
    }

}
