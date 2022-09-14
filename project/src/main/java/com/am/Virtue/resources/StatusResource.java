package com.am.Virtue.resources;

import com.am.Virtue.entities.Status;

public class StatusResource {
    private Long id;

    private String code;

    private String description;

    private String languageCode;


    public Status toStatus(StatusResource statusResource) {
        Status status = new Status();
        status.setId(statusResource.getId());
        status.setCode(statusResource.getCode());
        status.setDescription(statusResource.getDescription());
        status.setLanguageCode(statusResource.getLanguageCode());
        return status;
    }

    public StatusResource toResource(Status status) {
        StatusResource statusResource = new StatusResource();
        statusResource.setId(status.getId());
        statusResource.setCode(status.getCode());
        statusResource.setDescription(status.getDescription());
        statusResource.setLanguageCode(status.getLanguageCode());
        return statusResource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }


}

