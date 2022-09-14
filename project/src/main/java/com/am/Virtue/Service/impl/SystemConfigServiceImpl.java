package com.am.Virtue.Service.impl;


import com.am.Virtue.Service.SystemConfigService;
import com.am.Virtue.entities.Status;
import com.am.Virtue.entities.SystemConfig;
import com.am.Virtue.exception.ResourceException;
import com.am.Virtue.repository.StatusRepo;
import com.am.Virtue.repository.SystemConfigRepo;
import com.am.Virtue.repository.SystemMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SystemConfigServiceImpl implements SystemConfigService {
    @Autowired
    private SystemConfigRepo systemConfigRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private SystemMessageRepo messageRepo;

    @Override
    public SystemConfig findSystemConfigById(Long id) {
        return systemConfigRepo.findSystemConfigById(id);
    }

    @Override
    public SystemConfig createSystemConfig(SystemConfig systemConfig) {

        Status newSystemConfigstatus=statusRepo.findStatusById(11);
        if(newSystemConfigstatus==null)
            throw new ResourceException(HttpStatus.NOT_FOUND, messageRepo.findSystemMessageById(1l).getText());
        systemConfig.setStatus(newSystemConfigstatus);
        return systemConfigRepo.save(systemConfig);
    }

    @Override
    public SystemConfig findSystemConfigByKey(String key) {
        SystemConfig systemConfig = systemConfigRepo.findSystemConfigByKey(key);
        return systemConfig;
    }

    @Override
    public String deleteSystemConfigById(Long id) {
        return systemConfigRepo.deleteSystemConfigById(id);
    }

    @Override
    public SystemConfig update(SystemConfig systemConfig) {
        SystemConfig currentSystemConfig = systemConfigRepo.findSystemConfigById(systemConfig.getId());
        currentSystemConfig.setValue(systemConfig.getValue());
        return currentSystemConfig;
    }
}
