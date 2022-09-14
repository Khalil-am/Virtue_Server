package com.am.Virtue.Service.impl;

import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Status;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.AccountRepo;
import com.am.Virtue.repository.OperationLanguageRepo;
import com.am.Virtue.repository.StatusRepo;
import com.am.Virtue.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private OperationLanguageRepo operationLanguageRepo;

    @Override
    public Account findAccountById(Long id) {
        Account account = accountRepo.findAccountById(id);
        return account;
    }


    @Override
    public Account findAccountByEmailAndStatus(String email, Status status) {
        return accountRepo.findAccountByEmailAndStatus(email, status);
    }

    @Override
    public Account findAccountByMobileAndStatus(String mobile, Status status) {
        Account account = accountRepo.findAccountByMobileNumberAndStatus(mobile, status);
        return account;
    }

    @Override
    public Account findAccountByMobile(String mobile) {
        return null;
    }

    @Override
    public Account login(Account account) {
        OperationLanguage operationLanguage = operationLanguageRepo.findOperationLanguageByCode(account.getOperationLanguage().getCode());
        if (operationLanguage == null) {
            operationLanguage = operationLanguageRepo.findOperationLanguageByCode("en");
        }
        Status active = statusRepo.findStatusByCode("ACTIVE");
        if (active == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", operationLanguage.getCode());
        }
        Status Inactive = statusRepo.findStatusByCode("INACTIVE");
        if (Inactive == null) {
            throw new ResourceException(applicationContext, HttpStatus.BAD_REQUEST, "status404", operationLanguage.getCode());
        }
        // User credential
        String email = account.getEmail();
//        String password = account.getPassword();
        //String password = dcCripto.encrypt(account.getPassword());

        // Find Account by UserName and status Active & InActive
        Account findUser = accountRepo.findAccountByEmailAndStatus(email, active);
        if (findUser == null) {
            if (accountRepo.findAccountByEmailAndStatus(email, Inactive) != null) {
                throw new ResourceException(applicationContext, HttpStatus.FORBIDDEN, "Account423", operationLanguage.getCode());
            }
            throw new ResourceException(applicationContext, HttpStatus.FORBIDDEN, "Email404", operationLanguage.getCode());
        }
        return findUser;
    }

    @Override
    public Account changeAccountPassword(Account account, String newPassword) {
        Status status = statusRepo.findStatusByCode("ACTIVE");
        Account account1 = accountRepo.findAccountByMobileNumberAndStatus(account.getMobileNumber(), status);
        account1.setPassword(newPassword);
        return account1;
    }

    @Override
    public Account save(Account account) {
        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");
        if (activeStatus == null)
            throw new ResourceException(applicationContext, HttpStatus.NOT_FOUND, "sts404", account.getLangCode());

        Account currnetAccount = null;
        currnetAccount = accountRepo.findAccountByMobileNumberAndStatus(account.getMobileNumber(), activeStatus);
        if (currnetAccount != null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "acc302", account.getLangCode());

        currnetAccount = accountRepo.findAccountByEmailAndStatus(account.getEmail(), activeStatus);
        if (currnetAccount != null)
            throw new ResourceException(applicationContext, HttpStatus.FOUND, "acc302", account.getLangCode());

        account.setStatus(activeStatus);
        account.setCreationDate(LocalDateTime.now());
        return accountRepo.save(account);
    }
}