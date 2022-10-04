package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments")
public class Appointments {

    @GenericGenerator(
            name = "Appo_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "APPO_SEQ", value = "Appointments_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "Appo_Seq")
    private long id;
    @OneToOne
    @JoinColumn(name = "PSY_ACC_ID", referencedColumnName = "PSY_ACC_ID")
    private Psychologists psychologists;
    @OneToOne
    @JoinColumn(name = "ACC_ID", referencedColumnName = "ACC_ID")
    private Account account;
    @Column(name = "APPO_DATE")
    private String appoDate;
    @Column(name = "APPO_TIME")
    private String appoTime;
    @Column(name = "APPO_TYPE")
    private Long type;
    @Column(name = "APPO_CREATION_DATE")
    private LocalDateTime creationDate;
    @OneToOne
    @JoinColumn(name = "APPO_Sts_Id", referencedColumnName = "Sts_Id")
    private Status status;
    @OneToOne
    @JoinColumn(name = "APPO_Lang_Id")
    private OperationLanguage operationLanguage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Psychologists getPsychologists() {
        return psychologists;
    }

    public void setPsychologists(Psychologists psychologists) {
        this.psychologists = psychologists;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAppoDate() {
        return appoDate;
    }

    public void setAppoDate(String appoDate) {
        this.appoDate = appoDate;
    }

    public String getAppoTime() {
        return appoTime;
    }

    public void setAppoTime(String appoTime) {
        this.appoTime = appoTime;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public OperationLanguage getOperationLanguage() {
        return operationLanguage;
    }

    public void setOperationLanguage(OperationLanguage operationLanguage) {
        this.operationLanguage = operationLanguage;
    }
}
