package com.am.Virtue.Security;


import com.am.Virtue.Service.AccountService;
import com.am.Virtue.entities.Account;
import com.am.Virtue.exception.ResourceException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionHelper
{

    public static ApplicationContext applicationContext;
    public static AccountService accountService;

    private static Map<String, String> tokenHashTable = new HashMap<String, String>();
    public static Map<String, String> getTokenHashTable()
    {
        return tokenHashTable;
    }
    private static Map<String, HttpSession> sessionsHashTable = new HashMap<String, HttpSession>();


    public static String insertToken(String key, String value) {
        return tokenHashTable.put(key, value);
    }

    public static HttpSession insertSession(String key, HttpSession value) {
        return sessionsHashTable.put(key, value);
    }

    public static String updateToken(String key, String value) {
        return tokenHashTable.replace(key, value);
    }
    public static HttpSession updateSession(String key, HttpSession value) {
        return sessionsHashTable.replace(key, value);
    }

    public static String deleteToken(String key) {
            return tokenHashTable.remove(key);
        }

    public static HttpSession deleteSession(String key) {
        return sessionsHashTable.remove(key);
    }

    public static String getToken(String accountId)
    {
        return tokenHashTable.get(accountId);
    }

    public static HttpSession getSession(String accountId)
    {
        return sessionsHashTable.get(accountId);
    }

    public static String getAccountIdByToken(String token) {
        for (String accountId : tokenHashTable.keySet()) {
            if (tokenHashTable.get(accountId).equals(token)) {
                return accountId;
            }
        }
        return null;
    }

    public static String getAccountIdBySession(HttpSession session) {
        for (String accountId : sessionsHashTable.keySet()) {
            if (sessionsHashTable.get(accountId).equals(session)) {
                return accountId;
            }
        }
        return null;
    }

    public static boolean isValidSession(String accountId, String xAuthToken) {
        String sessionId = SessionHelper.getToken(accountId);

        return !(sessionId == null || !xAuthToken.equals(sessionId));
    }

    public static Account getAccountBySession(ApplicationContext context, HttpServletRequest request)
    {
        applicationContext = context;
        accountService = context.getBean(AccountService.class);

        String xAuthToken = request.getHeader("x-auth-token");
        String accountId = getAccountIdByToken(xAuthToken);

        if (accountId == null)
            throw new ResourceException(HttpStatus.UNAUTHORIZED, "x-auth-token UNAUTHORIZED");

        Account account=null;
//                = accountService.findAccountById(Utils.fetchAccountId(accountId));
        return account;
    }

    public <T> T getBean(Class<T> beanClass) {
        return this.applicationContext.getBean(beanClass);
    }

}
