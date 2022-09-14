package com.am.Virtue.Service;

import com.am.Virtue.entities.Status;

public interface StatusService {

    public Status findStatusByCode(String code);

    public Status createStatus(Status status);


}
