package com.am.Virtue.controller;


import com.am.Virtue.Service.*;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.resources.AccountResource;
import com.am.Virtue.Service.*;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private OperationLanguageService languageService;
    @Autowired
    private SystemMessageService systemMessageService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SystemMessageCaptionService systemMessageCaptionService;
    @Autowired
    private OTPService otpService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private SystemMessageService messageService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createNewUser(HttpServletRequest request, @RequestBody AccountResource accountResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (accountResource.getFirstName() == null ||
                accountResource.getLastName() == null ||
                accountResource.getMobileNumber() == null ||
                accountResource.getEmail() == null ||
                accountResource.getPassword() == null ||
                accountResource.getConfirmPassword() == null ||
                accountResource.getBirthdate() == null ||
                accountResource.getGenderId() == null
  //              accountResource.getOtp() == null
        ) throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());

        Account account = accountResource.toAccount();

        if (!Utils.isEmailCorrect(accountResource.getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }

        if (!accountResource.getPassword().equalsIgnoreCase(accountResource.getConfirmPassword())) {
            throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "password409", operationLanguage.getCode());
        }
        String validMobile = Utils.StandardPhoneFormat("+962", accountResource.getMobileNumber());
        if (validMobile.startsWith("0_")) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "mobile406", operationLanguage.getCode());
        }
        account.setMobileNumber(validMobile);
        Status status = statusService.findStatusByCode("ACTIVE");
        if (status == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Status404", operationLanguage.getCode());
        account.setStatus(status);
        OTP otp = otpService.findOTPByMobileNoAndStatus(validMobile, status);
     //   if (otp.getCode() == null)
     //       throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Otp404", operationLanguage.getCode());

     //   if (!accountResource.getOtp().equals(otp.getCode()))
       //     throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Otp406", operationLanguage.getCode());
        account.setLangCode(operationLanguage.getCode());
        /*
        Create Account
         */
        account = accountService.save(account);

        AccountResource resource = AccountResource.toAccountResource(account);
        /*
        Deactive OTP
         */
       // otpService.deactivateOTP(otp);

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption = systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, resource), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> loginAccount(HttpServletRequest request, HttpSession session, @RequestBody AccountResource accountResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }

        /*
        Validate Data
         */
        if (accountResource.getPassword() == null || accountResource.getEmail() == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "404", operationLanguage.getCode());


        Status active = statusService.findStatusByCode("ACTIVE");
        if (active == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", operationLanguage.getCode());

        Account account = null;

        /*
        Validate Username as  Email
         */
        String email = accountResource.getEmail();
        if (Utils.isEmailCorrect(accountResource.getEmail())) {
            if (Utils.isEmailCorrect(accountResource.getEmail())) {
                account = accountService.findAccountByEmailAndStatus(accountResource.getEmail().toLowerCase(), active);
                if (account == null)
                    throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "401", operationLanguage.getCode());
            }

        } else {
            account = accountService.findAccountByEmailAndStatus(email, active);
        }

        /*
        Set Account Token
         */

        String token = session.getId();
        AccountResource resource = AccountResource.toAccountResource(account);
        resource.setToken(token);


        SystemMessage message = systemMessageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption = systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, resource), HttpStatus.OK);
    }

    //fix
    @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
    public ResponseEntity<MessageBody> getProfile(HttpServletRequest request) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        /*
        fetch Account from Auth2.0 change once it ready
         */
//        AccountUserDetails accountUserDetails = (AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId = accountUserDetails.getUser().getId();
//        Account account = accountService.findAccountById();

        //      AccountResource accountResource = new AccountResource();
        // accountResource = accountResource.toAccountResource(account);
        SystemMessage message = messageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption = systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, message), HttpStatus.CREATED); //switch the messsage part for more accurate

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> updateProfile(HttpServletRequest request, @RequestBody AccountResource accountResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        if (accountResource.getFirstName() == null || accountResource.getLastName() == null || accountResource.getEmail() == null || accountResource.getMobileNumber() == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "404", languageCode);
        //commented for the Token
       /* AccountUserDetails accountUserDetails = (AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = accountUserDetails.getUser().getId();
        AgentUser AgentUser = agentUserService.findUserById((userId));
*/
        Status status = statusService.findStatusByCode("ACTIVE");
        Account account = accountService.findAccountByMobileAndStatus(accountResource.getMobileNumber(), status);
        Long userId = account.getId();

        if (userId == null)
            throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "401", languageCode);
        if (!Utils.isEmailCorrect(account.getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        if (account.getEmail() == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        if (account.getMobileNumber() == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        account.setFirstName(accountResource.getFirstName());
        account.setLastName(accountResource.getLastName());
        account.setEmail(accountResource.getEmail());
        accountResource = accountResource.toAccountResource(account);
        SystemMessage message = messageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption = systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, accountResource), HttpStatus.OK);

    }

    @RequestMapping(value = "/ForgetPassword", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> forgetPassword(HttpServletRequest request, @RequestBody AccountResource accountResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        if (accountResource.getEmail() == null || accountResource.getNewPassword() == null || accountResource.getConfirmPassword() == null) {
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "404", languageCode);
        }
        //fix
        Utils.isEmailCorrect(accountResource.getEmail());
        String AccountEmail = accountResource.getEmail();
        Status status = statusService.findStatusByCode("ACTIVE");
        if (status == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "Status404", operationLanguage.getCode());
        /*
         *change password to new password
         */
        Account account = accountService.findAccountByEmailAndStatus(accountResource.getEmail(), status); //accountResource.getEmail() -->>accountEmail
        if (!accountResource.getNewPassword().equals(accountResource.getConfirmPassword())) {
            throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "password409", operationLanguage.getCode());
        }
        account.setPassword(accountResource.getNewPassword());

        SystemMessage message = messageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption = systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, "Done"), HttpStatus.OK);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> changePassword(HttpServletRequest request, @RequestBody AccountResource accountResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        if (accountResource.getMobileNumber() == null || accountResource.getPassword() == null || accountResource.getNewPassword() == null || accountResource.getConfirmPassword() == null)
            throw new ResourceException(applicationContext, HttpStatus.FORBIDDEN, "403", languageCode);
        Status status = statusService.findStatusByCode("ACTIVE");
        String ValidMobile = Utils.StandardPhoneFormat("+966", accountResource.getMobileNumber());

        Account account = accountService.findAccountByMobileAndStatus(ValidMobile, status);

        if (!account.getStatus().getCode().equals("ACTIVE"))
            throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "401", languageCode);
        /*
         *Validate system user
         */

        if (!accountResource.getNewPassword().equals(accountResource.getConfirmPassword()))
            throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "password409", operationLanguage.getCode());

        if (accountResource.getNewPassword().equals(account.getPassword()))
            throw new ResourceException(applicationContext, HttpStatus.CONFLICT, "password409", operationLanguage.getCode());

        account.setOperationLanguage(operationLanguage);
        accountService.changeAccountPassword(account, accountResource.getNewPassword());


        SystemMessage message = messageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption = systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, "Done"), HttpStatus.OK);
    }

    /*
        @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
        public ResponseEntity<MessageBody> getProfile(HttpServletRequest request) {
            String languageCode = request.getHeader("lng");
            OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
            if (operationLanguage == null) {
                operationLanguage = languageService.findOperationLanguageByCode("en");
            }
            /*
            fetch Account from Redis
             */
    /*  AccountUserDetails accountUserDetails = (AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = accountUserDetails.getUser().getId();
        Account account = accountService.findAccountById(userId);

    AccountResource accountResource = new AccountResource();
    accountResource =accountResource.toAccountResource(account);
    SystemMessage message = messageService.findSystemMessageByCode("200");
    SystemMessageCaption systemMessageCaption =
            systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption,accountResource),HttpStatus.CREATED);
*/
}



