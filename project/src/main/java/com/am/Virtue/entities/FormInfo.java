package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Forms_info")
public class FormInfo {
    @GenericGenerator(
            name = "FORMS_INFO_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "FORMS_INFO_SEQ", value = "FORM_INFO_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "Forms_Info_id_Seq")
    @Id
    @Column(name = "FORMS_INFO_Id")
    private Long id;
    @Column(name = "FORMINFO_Desc")
    private String FormInfo_Desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormInfo_Desc() {
        return FormInfo_Desc;
    }

    public void setFormInfo_Desc(String formInfo_Desc) {
        FormInfo_Desc = formInfo_Desc;
    }
}

