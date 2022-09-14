package com.am.Virtue.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "City_Caption")
public class CityCaption {
    @GenericGenerator(
            name = "Cit_cpt_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "City_SEQ", value = "City_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "Cit_cpt_Seq")
    @Id
    @Column(name = "Cit_Cap_ID")
    private Long id;
    @Column(name = "Cit_Cap_Caption")
    private String caption;
    //    @Column(name = "Cit_Cap_description")
//    private String description;
    @ManyToOne
    @JoinColumn(name = "Cit_Cap_Cit_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "Cit_Cap_Lang_Id")
    private OperationLanguage operationLanguage;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public OperationLanguage getOperationLanguage() {
        return operationLanguage;
    }

    public void setOperationLanguage(OperationLanguage operationLanguage) {
        this.operationLanguage = operationLanguage;
    }
}