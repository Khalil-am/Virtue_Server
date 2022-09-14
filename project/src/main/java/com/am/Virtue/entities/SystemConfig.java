package com.am.Virtue.entities;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEM_CONFIG")
public class SystemConfig {

    @Id
    @SequenceGenerator(name = "SystemConfigSeq", sequenceName = "SYSTEM_CONFIG_SEQ",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SystemConfigSeq", strategy = GenerationType.AUTO)
    @Column(name = "Sys_Conf_ID")
    private Long id;

    @Column(name = "Sys_Conf_Key", nullable = false)
    private String key;

    @Column(name = "Sys_Conf_Value", nullable = false)
    private String value;

    @OneToOne
    @JoinColumn(name = "Sys_Conf_STS_id")
    private Status status;

    @Column(name = "sys_conf_description")
    private String description;

    @Transient
    private String languageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
