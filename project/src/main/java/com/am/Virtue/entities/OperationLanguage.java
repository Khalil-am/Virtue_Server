package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Operation_Language")
public class OperationLanguage {
    @Id
    @GenericGenerator(
            name = "OperationLanguageGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "OPERATION_LANGUAGE_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "OperationLanguageGenerator")
    @Column(name = "Opt_Lang_ID")
    private Long id;
    @Column(name = "Opt_Lang_Code")
    private String code;
    @Column(name = "Opt_Lang_Name")
    private String name;
    @Column(name = "Opt_Lang_Dir")
    private String direction;

    @JoinColumn(name = "Opt_Lang_sts_id")
    @OneToOne
    private Status statusId;

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status status) {
        this.statusId = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


}
