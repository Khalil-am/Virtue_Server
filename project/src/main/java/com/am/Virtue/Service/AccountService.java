package com.am.Virtue.Service;


import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.Status;

public interface AccountService {
    public Account findAccountById(Long id);

    public Account findAccountByEmailAndStatus(String email, Status status);

    public Account findAccountByMobileAndStatus(String mobile, Status status);

    public Account findAccountByMobile(String mobile);

    public Account save(Account account);

    public Account login(Account account);

    public Account changeAccountPassword(Account account, String newPassword);

    interface StatusService {

        public Status findStatusByCode(String code);

        public Status createStatus(Status status);


    }
}