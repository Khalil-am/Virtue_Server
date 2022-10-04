package com.am.Virtue.Security;

import com.am.Virtue.Service.AccountService;
import com.am.Virtue.Service.OperationLanguageService;
import com.am.Virtue.Service.StatusService;
import com.am.Virtue.Utils.Utils;
import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Status;
import com.am.Virtue.exception.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private StatusService statusService;

    @Autowired
    private OperationLanguageService operationLanguageService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {


        String userArray[] = name.split(",");

        Long userId = null;
        String username = null;
        String userType = null;
        String languageCode = null;

        try {
            userId = Long.parseLong(userArray[0]);
            username = userArray[1];
            userType = userArray[2];
            languageCode = userArray[3];

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "401", languageCode);
        }

        if (userId == null || username == null || userType == null || languageCode == null)
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "400", languageCode);

        switch (userType) {
            case "ACC":
                AccountUserDetails customerAccountUserDetails = this.findAccount(username, languageCode);
                return customerAccountUserDetails;
            default:
                throw new ResourceException(HttpStatus.BAD_REQUEST, "wrong credentials");
        }
    }

    private AccountUserDetails findAccount(String username, String languageCode) {
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
                if (account == null)
                    throw new ResourceException(applicationContext, HttpStatus.UNAUTHORIZED, "401", language.getCode());
                username = username.toLowerCase();
            }

        } else {
            account = accountService.findAccountByMobileAndStatus(mobile, active);
            username = mobile;
        }

        String usernameSession = "ACC" + "_" + username;

        return new AccountUserDetails(new User(account.getId(), usernameSession, account.getPassword(), account.getEmail(), "ACC", "ACC"));

    }


}

