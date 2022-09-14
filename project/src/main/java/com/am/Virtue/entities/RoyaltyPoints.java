package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Royalty_Points")
public class RoyaltyPoints {
    @Id
    @GenericGenerator(
            name = "RoyaltyPoints",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "RoyaltyPoints_SEQUANCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "RoyaltyPoints_ID")
    @Column(name = "ROYALITY_POINTS_ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "POINTS_CUSTOMER_ID", referencedColumnName = "ACC_ID")
    private Account Account;
    @Column(name = "ROYALITY_POINTS_RANK_ID")
    private Long PointsRankId;
    @Transient
    private String languageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return Account;
    }

    public void setAccount(Account account) {
        Account = account;
    }

    public Long getPointsRankId() {
        return PointsRankId;
    }

    public void setPointsRankId(Long pointsRankId) {
        PointsRankId = pointsRankId;
    }
}
