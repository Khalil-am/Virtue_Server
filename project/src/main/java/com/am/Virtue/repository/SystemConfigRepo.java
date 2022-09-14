package com.am.Virtue.repository;


import com.am.Virtue.entities.SystemConfig;
import org.springframework.data.repository.CrudRepository;

public interface SystemConfigRepo extends CrudRepository<SystemConfig, Long> {

    public SystemConfig findSystemConfigById(Long id);

    public SystemConfig save(SystemConfig systemConfig);

    public String deleteSystemConfigById(Long id);

    public SystemConfig findSystemConfigByKey(String key);

}
