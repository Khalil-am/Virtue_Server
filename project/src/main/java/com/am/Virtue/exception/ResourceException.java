package com.am.Virtue.exception;

import com.am.Virtue.entities.SystemMessageCaption;
import com.am.Virtue.Service.OperationLanguageService;
import com.am.Virtue.Service.SystemMessageCaptionService;
import com.am.Virtue.Service.SystemMessageService;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.SystemMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {

    private ApplicationContext applicationContext;
    private HttpStatus httpStatus;
    private SystemMessageService messageService;
    private OperationLanguageService OperationLanguageService;
    private SystemMessageCaptionService systemMessageCaptionService;

    public ResourceException(ApplicationContext applicationContext, HttpStatus httpStatus, String msgCode, String langCode) //might brake soon
    {
        String caption = "No Caption";
        this.applicationContext = applicationContext;
        this.OperationLanguageService = this.getBean(com.am.Virtue.Service.OperationLanguageService.class); //might break
        this.messageService = this.getBean(SystemMessageService.class);
        this.systemMessageCaptionService = this.getBean(SystemMessageCaptionService.class);
        if (msgCode == null)
            throw new ResourceException(HttpStatus.NOT_FOUND, "Message Code Not Found");
        if (langCode == null)
            langCode = "en";
        OperationLanguage operationLanguage = OperationLanguageService.findOperationLanguageByCode(langCode);
        SystemMessage message = messageService.findSystemMessageByCode(msgCode);
        if (message == null)
            throw new ResourceException(HttpStatus.NOT_FOUND, "System Message Not Found for code: " + msgCode);
        SystemMessageCaption systemMessageCaption = systemMessageCaptionService.findMessageCaptionByMessageAndLanguage(message, operationLanguage);
        if (systemMessageCaption == null)
            caption = message.getDescription();
        else
            caption = systemMessageCaption.getCaption();
        throw new ResourceException(httpStatus, caption);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ResourceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public <T> T getBean(Class<T> beanClass) {
        return this.applicationContext.getBean(beanClass);
    }
}
