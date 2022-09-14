package com.am.Virtue.controller;

import com.am.Virtue.Service.*;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.resources.AccountResource;
import com.am.Virtue.resources.AppointmentsResource;
import com.am.Virtue.resources.PsychologistsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/Appointments")
public class AppointmentsController {

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
    @Autowired
    private AppointmentsService appointmentsService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createNewAppointment(HttpServletRequest request, @RequestBody AppointmentsResource appointmentsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (
                appointmentsResource.getAccount() == null
                        || appointmentsResource.getPsychologists() == null
                        || appointmentsResource.getAppoTime() == null
                        || appointmentsResource.getAppoDate() == null
        )
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        Appointments appointments = appointmentsResource.toAppointments();

        if (!Utils.isEmailCorrect(appointmentsResource.getAccount().getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        if (!Utils.isEmailCorrect(appointmentsResource.getPsychologists().getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        /*make an if statement checks the appointments and makes sure the therapists
        and the client doesnt have any other appointments */
        appointments = appointmentsService.save(appointments);

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, appointmentsResource), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> RemoveAppointment(HttpServletRequest request, @RequestBody AppointmentsResource appointmentsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (
                appointmentsResource.getAccount() == null ||
                        appointmentsResource.getAppoDate() == null
        )
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        Appointments appointments = appointmentsResource.toAppointments();

        if (!Utils.isEmailCorrect(appointmentsResource.getAccount().getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        if (!Utils.isEmailCorrect(appointmentsResource.getPsychologists().getEmail())) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "email406", operationLanguage.getCode());
        }
        /*make an if statement checks the appointments and makes sure the therapists
        and the client doesnt have any other appointments */
        appointments = appointmentsService.save(appointments);
        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, appointmentsResource), HttpStatus.CREATED);
    }

}