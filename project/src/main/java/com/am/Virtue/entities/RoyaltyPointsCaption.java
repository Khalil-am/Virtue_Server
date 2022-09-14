package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Royalty_Points_Caption")
public class RoyaltyPointsCaption {
    @Id
    @GenericGenerator(
            name = "RoyaltyPointsCaption",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "RoyaltyPointsCaption_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "RoyaltyPointsCaptionGenerator")
    @Column(name = "Rlty_Poi_Cap_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROYALITY_POINTS_ID", referencedColumnName = "ROYALITY_POINTS_ID")
    private RoyaltyPoints royaltyPoints;

    @OneToOne
    @JoinColumn(name = "Rlty_Poi_Lang_Id")
    private OperationLanguage operationLanguage;

    @Column(name = "Rlty_Poi_Cap_Name")
    private String caption;

    @Transient
    private String languageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoyaltyPoints getRoyaltyPoints() {
        return royaltyPoints;
    }

    public void setRoyaltyPoints(RoyaltyPoints royaltyPoints) {
        this.royaltyPoints = royaltyPoints;
    }

    public OperationLanguage getOperationLanguage() {
        return operationLanguage;
    }

    public void setOperationLanguage(OperationLanguage operationLanguage) {
        this.operationLanguage = operationLanguage;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
