package com.am.Virtue.controller;

import com.am.Virtue.Service.*;
import com.am.Virtue.Service.impl.FileDbServiceImpl;
import com.am.Virtue.config.MessageBody;
import com.am.Virtue.config.MessageHandler;
import com.am.Virtue.entities.FileDb;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.SystemMessage;
import com.am.Virtue.entities.SystemMessageCaption;
import com.am.Virtue.resources.AppointmentsResource;
import com.am.Virtue.resources.DiagnosisFormResource;
import com.am.Virtue.resources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileDbController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SystemMessageCaptionService systemMessageCaptionService;
    @Autowired
    private FileDbServiceImpl fileDbService;
    @Autowired
    private OperationLanguageService operationLanguageService;

    @Autowired
    private StatusService statusService;
    @Autowired
    private SystemMessageService messageService;


    @PostMapping
    public FileResource uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileDbService.store(file);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> uploadFileId(HttpServletRequest request,  MultipartFile file) throws IOException {
        String languageCode = request.getHeader("lng");
        OperationLanguage operationLanguage = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (operationLanguage == null) {
            operationLanguage = operationLanguageService.findOperationLanguageByCode("en");
        }

        fileDbService.store(file);
        SystemMessage message = messageService.findSystemMessageByCode("201");
        SystemMessageCaption systemMessageCaption =
                systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        return new ResponseEntity<>(
                MessageHandler.setMessageBody(systemMessageCaption, "Success"), HttpStatus.CREATED);


    }

    @GetMapping("/{id}")
    public FileDb getFile(@PathVariable String id) {

        return fileDbService.getFileById(id);

    }

    @GetMapping("/list")
    public List<FileResource> getFileList() {
        return fileDbService.getFileList();
    }


}
