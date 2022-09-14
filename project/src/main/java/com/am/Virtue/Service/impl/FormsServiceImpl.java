package com.am.Virtue.Service.impl;

import com.am.Virtue.Service.CityService;
import com.am.Virtue.Service.FormsService;
import com.am.Virtue.entities.City;
import com.am.Virtue.entities.Forms;
import com.am.Virtue.repository.CityRepo;
import com.am.Virtue.repository.FormsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FormsServiceImpl implements FormsService {
    @Autowired
    private FormsRepo formsRepo;

    @Override
    public Forms findFormsById(long id) {
        return formsRepo.findFormsById(id);
    }
}
