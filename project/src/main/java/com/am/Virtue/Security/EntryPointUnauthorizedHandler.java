package com.am.Virtue.Security;

import com.am.Virtue.Service.AccountService;
import com.am.Virtue.Service.OperationLanguageService;
import com.am.Virtue.Service.StatusService;
import com.am.Virtue.Service.SystemMessageService;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Status;
import com.am.Virtue.exception.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Autowired
    private SystemMessageService messageService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private OperationLanguageService operationLanguageService;


    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationContext applicationContext;
    @Lazy
    @Autowired
    private DcCripto dcCripto;
    @Autowired
    private SimpleCORSFilter simpleCORSFilter;

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization != null) {
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            final String[] values = credentials.split(":", 2);

            Long userID = Long.parseLong(values[0].split(",")[0]);
            String username = values[0].split(",")[1];
            username = username.toLowerCase();

           String password = dcCripto.encrypt(values[1]);

            String userType = values[0].split(",")[2];
            String languageCode = values[0].split(",")[3];
            Account account = null;

            switch (userType) {
                case "CUS":
                    OperationLanguage language = operationLanguageService.findOperationLanguageByCode(languageCode);
                    if (language == null)
                        language = operationLanguageService.findOperationLanguageByCode("en");

                    Status active = statusService.findStatusByCode("ACTIVE");
                    Status created = statusService.findStatusByCode("0");

                    if (active == null)
                        throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "status404", language.getCode());

                    account = findAccount(username, languageCode);
                    if (account == null)
                        throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "401", language.getCode());


                    break;
            }
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
        }
    }

    private Account findAccount(String username, String languageCode) {
        OperationLanguage language = operationLanguageService.findOperationLanguageByCode(languageCode);
        if (language == null)
            language = operationLanguageService.findOperationLanguageByCode("en");

        Status active = statusService.findStatusByCode("ACTIVE");
        if (active == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", language.getCode());

        Account account = null;
        String mobile = Utils.StandardPhoneFormat("+962", username);
        if (mobile.startsWith("0_")) {
            if (Utils.isEmailCorrect(username)) {
                account = accountService.findAccountByEmailAndStatus(username.toLowerCase(), active);
                username = username.toLowerCase();
            }

        } else {
            account = accountService.findAccountByMobileAndStatus(mobile, active);
            username = mobile;
        }
        return account;
    }

}
