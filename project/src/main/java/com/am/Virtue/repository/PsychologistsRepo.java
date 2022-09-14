package com.am.Virtue.repository;

import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.OperationLanguage;
import com.am.Virtue.entities.Psychologists;
import com.am.Virtue.entities.Status;
import org.springframework.data.repository.CrudRepository;

import java.lang.ref.PhantomReference;
import java.util.List;

public interface PsychologistsRepo extends CrudRepository<Psychologists, Long> {


    public Psychologists findPsychologistsById(Long id);


    public Psychologists findPsychologistsByEmailAndStatus(String email, Status status);

    public Psychologists findPsychologistsByMobileNumberAndStatus(String mobile, Status status);

    public Psychologists findPsychologistsByMobileNumber(String mobile);

    public Psychologists save(Psychologists psychologists);

    public List<Psychologists> findAllByOperationLanguage(OperationLanguage operationLanguage );
}
