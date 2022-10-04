package com.am.Virtue.controller;

import com.am.Virtue.Service.*;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.StatusRepo;
import com.am.Virtue.resources.AppointmentsResource;
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
    @Autowired
    private PsychologistsService psychologistsService;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private OperationLanguageService operationLanguageService;
    //TODO fix to add only the Account id to the appointment
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
                appointmentsResource.getPsychologists() == null ||
                        appointmentsResource.getAccount() == null ||
                        appointmentsResource.getTime() == null ||
                        appointmentsResource.getDate() == null ||
                        appointmentsResource.getType() == null
        )
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());

        Status active = statusService.findStatusByCode("ACTIVE");
        if (active == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", operationLanguage.getCode());
          /*
    Validate Psy and Account By id
     */
        Account account = accountService.findAccountById((Long.parseLong(appointmentsResource.getAccount())));
        if (account == null)
            throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "Acc401", operationLanguage.getCode());

        Psychologists psychologists = psychologistsService.findPsychologistsById(Long.parseLong(appointmentsResource.getPsychologists()));
        if (psychologists == null)
            throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "Psy401", operationLanguage.getCode());

        Appointments appointments = appointmentsResource.toAppointments();
        appointments.setPsychologists(psychologists);
        appointments.setAccount(account);
        appointments.setOperationLanguage(operationLanguage);
           /*
    Validate Appointments By date and time
     */
        //fix for not having two appointments at once
        // Appointments appoAcc = appointmentsService.findAppointmentsByAccId(account.getId());
        //    if (appointmentsResource.getTime().equals(appoAcc.getAppoTime())) {
        //      throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "AppoAccExists406", operationLanguage.getCode());}
        //Appointments appoPsy = appointmentsService.findPsychologists_AppointmentsById(psychologists.getId());
        // if (appoPsy.getAppoTime().equals(appointmentsResource.getTime())) {
        //   throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "AppoAccExists406", operationLanguage.getCode());}
        //   Appointments appo = appointmentsService.findAppointmentsByDateAndTime(appointments.getAppoDate(), appointments.getAppoTime());
        //  if (appo != null) {
        //   throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "AppoExists401", operationLanguage.getCode());}
           /*
            Save Appointments
             */
        appointments = appointmentsService.save(appointments);

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, "Success"), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/addPsy", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createNewAppointmentPsy(HttpServletRequest request, @RequestBody AppointmentsResource appointmentsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (
                appointmentsResource.getPsychologists() == null ||
                        appointmentsResource.getTime() == null ||
                        appointmentsResource.getDate() == null ||
                        appointmentsResource.getType() == null
        )
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());

        Status active = statusService.findStatusByCode("ACTIVE");
        if (active == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", operationLanguage.getCode());
          /*
    Validate Psy By id
     */

        Psychologists psychologists = psychologistsService.findPsychologistsById(Long.parseLong(appointmentsResource.getPsychologists()));
        if (psychologists == null)
            throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "Psy401", operationLanguage.getCode());

        Appointments appointments = appointmentsResource.toAppointments();
        appointments.setPsychologists(psychologists);
        appointments.setOperationLanguage(operationLanguage);
           /*
    Validate Appointments By date and time
     */
        //fix for not having two appointments at once
        // Appointments appoAcc = appointmentsService.findAppointmentsByAccId(account.getId());
        //    if (appointmentsResource.getTime().equals(appoAcc.getAppoTime())) {
        //      throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "AppoAccExists406", operationLanguage.getCode());}
        //Appointments appoPsy = appointmentsService.findPsychologists_AppointmentsById(psychologists.getId());
        // if (appoPsy.getAppoTime().equals(appointmentsResource.getTime())) {
        //   throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "AppoAccExists406", operationLanguage.getCode());}
        //   Appointments appo = appointmentsService.findAppointmentsByDateAndTime(appointments.getAppoDate(), appointments.getAppoTime());
        //  if (appo != null) {
        //   throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "AppoExists401", operationLanguage.getCode());}
           /*
            Save Appointments
             */
        appointments = appointmentsService.save(appointments);

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, "Success"), HttpStatus.CREATED);

    }


    @RequestMapping(value = "/removeAccAppo", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> RemoveAppointment(HttpServletRequest request, @RequestBody AppointmentsResource appointmentsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (appointmentsResource.getId() == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        Appointments appointments = appointmentsResource.toAppointmentsId();
        Appointments appointments1 = appointmentsService.findAppointmentsByAccId(appointments.getId());

        appointmentsService.deactivateAppointmentById(appointments1.getId());

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, "Success"), HttpStatus.OK);
    }
    @RequestMapping(value = "/removePsyAppo", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> RemovePsyAppointment(HttpServletRequest request, @RequestBody AppointmentsResource appointmentsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = languageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = languageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (appointmentsResource.getId() == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        Appointments appointments = appointmentsResource.toAppointmentsId();
        Appointments appointments1 = appointmentsService.findPsychologists_AppointmentsById(appointments.getId());

        appointmentsService.deactivateAppointmentById(appointments1.getId());

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, "Success"), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResponseEntity<MessageBody> findAll(HttpServletRequest request) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null)
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");

        List<Appointments> appointments = appointmentsService.findAllByOperationLanguage(operationLanguage);
        List<AppointmentsResource> captionResources = AppointmentsResource.toAppointmentsResource(appointments);
        SystemMessage message = systemMessageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, captionResources), HttpStatus.OK);
    }

    @RequestMapping(value = "findAllById", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> findAllById(HttpServletRequest request, @RequestBody AppointmentsResource appointmentsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null)
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        if (appointmentsResource.getId() == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());
        Appointments appointments1 = appointmentsResource.toAppointmentsId();
        // Appointments appointments1 = appointmentsService.findAppointmentsByAccId(appointments.getId());
//make Operation language and id filttering 12:00AM time of request
        List<Appointments> appointments = appointmentsService.findAllByAccountId(appointments1.getId());
        List<AppointmentsResource> captionResources = AppointmentsResource.toAppointmentsResource(appointments);
        SystemMessage message = systemMessageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, captionResources), HttpStatus.OK);
    }
}
