package com.am.Virtue.controller;

import com.am.Virtue.Service.*;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.resources.OTPResource;
import com.am.Virtue.resources.PsychologistsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Psy")
public class PsychologistsController {
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
    private PsychologistsService psychologistsService;
    @Autowired
    private OperationLanguageService operationLanguageService;
    @Autowired
    private  SystemMessageService systemMessageService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createNewUser(HttpServletRequest request, @RequestBody PsychologistsResource psychologistsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (psychologistsResource.getFirstName() == null
                || psychologistsResource.getLastName() == null
                || psychologistsResource.getMobileNumber() == null
                || psychologistsResource.getEmail() == null
                || psychologistsResource.getPassword() == null
                || psychologistsResource.getConfirmPassword() == null
                || psychologistsResource.getBirthdate() == null
        )
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());

        Psychologists psychologists = psychologistsResource.toPsychologists();

        if (!Utils.isEmailCorrect(psychologistsResource.getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }

        if (!psychologistsResource.getPassword().equalsIgnoreCase(psychologistsResource.getConfirmPassword())) {
            throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "password409", operationLanguage.getCode());
        }
        String validMobile = Utils.StandardPhoneFormat("+962", psychologistsResource.getMobileNumber());
        if (validMobile.startsWith("0_")) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "mobile406", operationLanguage.getCode());
        }
        psychologists.setMobileNumber(validMobile);
        Status status = statusService.findStatusByCode("ACTIVE");
        if (status == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Status404", operationLanguage.getCode());
        psychologists.setStatus(status);
//        OTP otp = otpService.findOTPByMobileNoAndStatus(validMobile, status);
//        if (otp.getCode() == null)
//            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Otp404", operationLanguage.getCode());
//
//        if (!accountResource.getOtp().equals(otp.getCode()))
//            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Otp406", operationLanguage.getCode());
//        account.setLangCode(operationLanguage.getCode());
        /*
        Create Account
         */
        psychologists = psychologistsService.save(psychologists);

        PsychologistsResource resource = PsychologistsResource.toPsychologistsResource(psychologists);
        /*
        Deactive OTP
         */
        //      otpService.deactivateOTP(otp);

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, resource), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> PsychologistRegistrationOTP(HttpServletRequest request, @RequestBody PsychologistsResource psychologistsResource) {
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
        if (psychologistsResource.getFirstName() == null ||
                psychologistsResource.getLastName() == null ||
                psychologistsResource.getMobileNumber() == null ||
                psychologistsResource.getEmail() == null ||
                psychologistsResource.getPassword() == null ||
                psychologistsResource.getConfirmPassword() == null ||
                psychologistsResource.getGender() == null
        ) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        }

        Status status = statusService.findStatusByCode("ACTIVE");

        Psychologists psychologists = new Psychologists();
        if (!Utils.isEmailCorrect(psychologistsResource.getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        //fix
        Psychologists currentAccountEmail = psychologistsService.findPsychologistsByEmailAndStatus(psychologistsResource.getEmail(), status);
        if (currentAccountEmail.equals(null)) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }

        psychologists.setEmail(psychologistsResource.getEmail());
        if (!psychologistsResource.getPassword().equalsIgnoreCase(psychologistsResource.getConfirmPassword())) {
            throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "password409", operationLanguage.getCode());
        }

        // String enciptPassword="";//fix after adding vault
        psychologists.setPassword(psychologistsResource.getPassword());
        String validMobile = Utils.StandardPhoneFormat("+962", psychologistsResource.getMobileNumber());
        if (validMobile.startsWith("0_")) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "mobile406", operationLanguage.getCode());
        }
        Account currentAccountMobile = accountService.findAccountByMobileAndStatus(validMobile, status);
        if (currentAccountMobile != null) {
            throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "mobile406", operationLanguage.getCode());
        }
        psychologists.setMobileNumber(validMobile);
        String userName = psychologistsResource.getEmail().toLowerCase();
        Account findByUserName = accountService.findAccountByEmailAndStatus(userName, status);
        if (findByUserName != null) {
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "Username302", operationLanguage.getCode());
        }
        /*
         *Create OTP object to pass throw service and persist in database
         */
        OTP otp = new OTP();
        otp.setMobileNo(validMobile);
        otp.setEmail(psychologistsResource.getEmail());
        otp = otpService.createOTP(otp);

        OTPResource otpResource = OTPResource.toResource(otp);
        otpResource.setCode("");

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, otpResource), HttpStatus.CREATED);
    }


    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResponseEntity<MessageBody> findAll(HttpServletRequest request) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null)
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        List<Psychologists> psychologists = psychologistsService.findAllByOperationLanguage(operationLanguage);
        List<PsychologistsResource> captionResources = PsychologistsResource.toResource(psychologists);
        SystemMessage message = systemMessageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, captionResources), HttpStatus.OK);
    }
}