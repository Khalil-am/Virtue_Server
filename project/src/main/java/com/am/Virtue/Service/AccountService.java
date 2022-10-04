package com.am.Virtue.Service;


import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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