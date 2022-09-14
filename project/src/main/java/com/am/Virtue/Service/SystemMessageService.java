package com.am.Virtue.Service;

import com.am.Virtue.entities.SystemMessage;

public interface SystemMessageService {
    public SystemMessage findSystemMessageByCode(String code);
    public SystemMessage createSystemMessage(SystemMessage systemMessage);

}
