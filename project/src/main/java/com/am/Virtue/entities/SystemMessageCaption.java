package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "System_Message_Caption")
public class SystemMessageCaption {
    @Id
    @GenericGenerator(
            name = "System_MSG_Caption_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "SYSTEM_MESSAGE_CAPTION_SEQ", value = "SM_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "System_MSG_Caption_Seq")
    @Column(name = "System_Message_id")
    private Long id;

    @Column(name = "SM_Cap_Name")
    private String caption;

    @ManyToOne
    @JoinColumn(name = "SM_Cap_Lang_Id")
    private OperationLanguage operationLanguage;
    @ManyToOne
    @JoinColumn(name = "SM_Id")
    private SystemMessage systemMessage;

    @Transient
    private String languageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public OperationLanguage getOperationLanguage() {
        return operationLanguage;
    }

    public void setOperationLanguage(OperationLanguage operationLanguage) {
        this.operationLanguage = operationLanguage;
    }

    public SystemMessage getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(SystemMessage systemMessage) {
        this.systemMessage = systemMessage;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}

