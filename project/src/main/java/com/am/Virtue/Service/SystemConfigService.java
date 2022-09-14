package com.am.Virtue.Service;

import com.am.Virtue.entities.SystemConfig;

public interface SystemConfigService {
    public SystemConfig findSystemConfigById(Long id);

    public SystemConfig createSystemConfig(SystemConfig systemConfig);

    public SystemConfig findSystemConfigByKey(String key);

    public String deleteSystemConfigById(Long id);

    public SystemConfig update(SystemConfig systemConfig);
}
