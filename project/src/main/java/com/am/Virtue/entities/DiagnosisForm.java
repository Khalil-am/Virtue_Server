package com.am.Virtue.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Diagnosis_Form")
public class DiagnosisForm {
    @GenericGenerator(
            name = "Dia_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "Dia_Seq", value = "DIAGNOSIS_FORM_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "DIA_SEQ")
    @Column(name = "Diagnosis_Form_ID")
    private Long id;

    @Column(name = "Q1")
    private Long Q1;
    @Column(name = "Q2")
    private Long Q2;
    @Column(name = "Q3")
    private Long Q3;
    @Column(name = "Q4")
    private Long Q4;
    @Column(name = "Q5")
    private Long Q5;
    @Column(name = "Q6")
    private Long Q6;
    @Column(name = "Q7")
    private Long Q7;
    @Column(name = "Q8")
    private Long Q8;
    @Column(name = "Q9")
    private Long Q9;
    @Column(name = "Q10")
    private Long Q10;
    @Column(name = "Q11")
    private Long Q11;
    @Column(name = "Q12")
    private Long Q12;
    @Column(name = "Q13")
    private Long Q13;
    @Column(name = "Q14")
    private Long Q14;
    @Column(name = "Q15")
    private Long Q15;
    @Column(name = "Q16")
    private Long Q16;
    @Column(name = "Q17")
    private Long Q17;
    @Column(name = "Q18")
    private Long Q18;
    @Column(name = "Q19")
    private Long Q19;
    @Column(name = "Q20")
    private Long Q20;
    @Column(name = "Q21")
    private Long Q21;
    @Column(name = "Q22")
    private Long Q22;
    @Column(name = "Q23")
    private Long Q23;
    @Column(name = "Q24")
    private Long Q24;
    @Column(name = "Diagnosis")
    private String Diagnosis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQ1() {
        return Q1;
    }

    public void setQ1(Long q1) {
        Q1 = q1;
    }

    public Long getQ2() {
        return Q2;
    }

    public void setQ2(Long q2) {
        Q2 = q2;
    }

    public Long getQ3() {
        return Q3;
    }

    public void setQ3(Long q3) {
        Q3 = q3;
    }

    public Long getQ4() {
        return Q4;
    }

    public void setQ4(Long q4) {
        Q4 = q4;
    }

    public Long getQ5() {
        return Q5;
    }

    public void setQ5(Long q5) {
        Q5 = q5;
    }

    public Long getQ6() {
        return Q6;
    }

    public void setQ6(Long q6) {
        Q6 = q6;
    }

    public Long getQ7() {
        return Q7;
    }

    public void setQ7(Long q7) {
        Q7 = q7;
    }

    public Long getQ8() {
        return Q8;
    }

    public void setQ8(Long q8) {
        Q8 = q8;
    }

    public Long getQ9() {
        return Q9;
    }

    public void setQ9(Long q9) {
        Q9 = q9;
    }

    public Long getQ10() {
        return Q10;
    }

    public void setQ10(Long q10) {
        Q10 = q10;
    }

    public Long getQ11() {
        return Q11;
    }

    public void setQ11(Long q11) {
        Q11 = q11;
    }

    public Long getQ12() {
        return Q12;
    }

    public void setQ12(Long q12) {
        Q12 = q12;
    }

    public Long getQ13() {
        return Q13;
    }

    public void setQ13(Long q13) {
        Q13 = q13;
    }

    public Long getQ14() {
        return Q14;
    }

    public void setQ14(Long q14) {
        Q14 = q14;
    }

    public Long getQ15() {
        return Q15;
    }

    public void setQ15(Long q15) {
        Q15 = q15;
    }

    public Long getQ16() {
        return Q16;
    }

    public void setQ16(Long q16) {
        Q16 = q16;
    }

    public Long getQ17() {
        return Q17;
    }

    public void setQ17(Long q17) {
        Q17 = q17;
    }

    public Long getQ18() {
        return Q18;
    }

    public void setQ18(Long q18) {
        Q18 = q18;
    }

    public Long getQ19() {
        return Q19;
    }

    public void setQ19(Long q19) {
        Q19 = q19;
    }

    public Long getQ20() {
        return Q20;
    }

    public void setQ20(Long q20) {
        Q20 = q20;
    }

    public Long getQ21() {
        return Q21;
    }

    public void setQ21(Long q21) {
        Q21 = q21;
    }

    public Long getQ22() {
        return Q22;
    }

    public void setQ22(Long q22) {
        Q22 = q22;
    }

    public Long getQ23() {
        return Q23;
    }

    public void setQ23(Long q23) {
        Q23 = q23;
    }

    public Long getQ24() {
        return Q24;
    }

    public void setQ24(Long q24) {
        Q24 = q24;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }
}
