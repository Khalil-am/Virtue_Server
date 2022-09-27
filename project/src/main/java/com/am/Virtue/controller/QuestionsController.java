package com.am.Virtue.controller;

import com.am.Virtue.Service.*;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.entities.*;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.resources.PsychologistsResource;
import com.am.Virtue.resources.QuestionsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Questions")
public class QuestionsController {
    @Autowired
    private SystemMessageService messageService;

    @Autowired
    private SystemMessageCaptionService systemMessageCaptionService;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private QuestionsSerivce questionsSerivce;
    @Autowired
    private OperationLanguageService operationLanguageService;
    @Autowired
    private SystemMessageService systemMessageService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createNewUser(HttpServletRequest request, @RequestBody QuestionsResource questionsResource) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        }
        /*
        Validate Data
         */
        if (
                questionsResource.getId() == null
        )
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", operationLanguage.getCode());

        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(MessageHandler.setMessageBody(systemMessageCaption, questionsResource), HttpStatus.CREATED);
    }


    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResponseEntity<MessageBody> findAll(HttpServletRequest request) {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null)
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        List<Questions> questions = questionsSerivce.findAllByOperationLanguage(operationLanguage);
      //  List<QuestionsResource> captionResources = QuestionsResource.toResource(questions);
        SystemMessage message = systemMessageService.findSystemMessageByCode("200");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);

        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, null/*change once fixed captionResources*/), HttpStatus.OK);
    }


}
