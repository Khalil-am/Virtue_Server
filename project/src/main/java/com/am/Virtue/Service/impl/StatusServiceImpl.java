package com.am.Virtue.Service.impl;


import com.am.Virtue.Service.StatusService;
import com.am.Virtue.entities.Status;
import com.am.Virtue.repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {


    @Autowired
    StatusRepo statusRepo;

    @Override
    public Status findStatusByCode(String code) {
        return statusRepo.findStatusByCode(code);
    }

    @Override
    public Status createStatus(Status status) {
        return statusRepo.save(status);
    }
}

