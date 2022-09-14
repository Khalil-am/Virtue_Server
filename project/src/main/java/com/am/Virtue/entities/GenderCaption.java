package com.am.Virtue.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Gender_Caption")
public class GenderCaption {


    @Id
    @GenericGenerator(
            name = "GenderCaptionGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "GenderCaption_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "GenderCaptionGenerator")
    @Column(name = "GenCap_ID")
    private Long id;
    @Column(name = "GenCap_Caption")
    private String caption;
    @ManyToOne
    @JoinColumn(name = "GenCap_lang_Id",referencedColumnName = "Opt_Lang_ID")
    private OperationLanguage operationLanguage;
    @ManyToOne
    @JoinColumn(name = "GenCap_Gender_ID",referencedColumnName = "Gender_Id")
    private Gender gender;


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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

