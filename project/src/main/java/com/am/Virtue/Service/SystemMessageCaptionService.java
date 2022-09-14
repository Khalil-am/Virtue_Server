package com.am.Virtue.Service;

import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.SystemMessage;
import com.am.Virtue.entities.SystemMessageCaption;

public interface SystemMessageCaptionService {
    public SystemMessageCaption findMessageCaptionByMessageAndLanguage(SystemMessage systemMessage , OperationLanguage operationLanguage);

}