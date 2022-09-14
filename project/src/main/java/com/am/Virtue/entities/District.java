package com.am.Virtue.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "District")
public class District implements Serializable {

    @GenericGenerator(
            name = "Dis_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "Dis_Seq", value = "District_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @Id
    @GeneratedValue(generator = "Dis_Seq")
    @Column(name = "dst_ID")
    private Long id;

    @Column(name = "dst_code")
    private String code;

    @Column(name = "dst_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "dst_City_Id")
    private City city;


    @OneToOne
    @JoinColumn(name = "dst_sts_id",referencedColumnName = "Sts_Id")
    private Status status;

    @Transient
    private String languageCode;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
