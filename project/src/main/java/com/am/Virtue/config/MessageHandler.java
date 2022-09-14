package com.am.Virtue.config;

import com.am.Virtue.entities.SystemMessageCaption;

public class MessageHandler {

    public static MessageBody setMessageBody(SystemMessageCaption systemMessageCaption, Object obj) {
        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus(systemMessageCaption.getSystemMessage().getCode());
        messageBody.setMsgWithLanguage(systemMessageCaption.getOperationLanguage().getCode());
        messageBody.setText(systemMessageCaption.getCaption());
        messageBody.setBody(obj);

        return messageBody;
    }
}