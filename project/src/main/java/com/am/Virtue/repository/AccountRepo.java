package com.am.Virtue.repository;

import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.Status;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {
    public Account findAccountById(Long id);


    public Account findAccountByEmailAndStatus(String email, Status status);

    public Account findAccountByMobileNumberAndStatus(String mobile, Status status);

    public Account save(Account account);
}
