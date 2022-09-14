package com.am.Virtue.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "APPO_DATE")
    private Date AppoDate;
    @Column(name = "APPO_TIME")
    private Date AppoTime;
    @Column(name = "APPO_CREATION_DATE")
    private LocalDateTime creationDate;
    @OneToOne
    @JoinColumn(name = "ACC_Sts_Id", referencedColumnName = "Sts_Id")
    private Status status;

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

    public Date getAppoDate() {
        return AppoDate;
    }

    public void setAppoDate(Date appoDate) {
        AppoDate = appoDate;
    }

    public Date getAppoTime() {
        return AppoTime;
    }

    public void setAppoTime(Date appoTime) {
        AppoTime = appoTime;
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
}
