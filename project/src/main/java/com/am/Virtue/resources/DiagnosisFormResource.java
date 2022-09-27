package com.am.Virtue.resources;

import com.am.Virtue.entities.DiagnosisForm;
import com.am.Virtue.entities.OTP;
import com.am.Virtue.repository.DiagnosisFormRepo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DiagnosisFormResource {

    @Id
    @GeneratedValue(generator = "DIA_SEQ")
    @Column(name = "Diagnosis_Form_ID")
    private Long id;


    private Long Q1;

    private Long Q2;

    private Long Q3;

    private Long Q4;

    private Long Q5;
    private Long Q6;
    private Long Q7;
    private Long Q8;
    private Long Q9;
    private Long Q10;
    private Long Q11;
    private Long Q12;
    private Long Q13;
    private Long Q14;
    private Long Q15;
    private Long Q16;
    private Long Q17;
    private Long Q18;
    private Long Q19;
    private Long Q20;
    private Long Q21;
    private Long Q22;
    private Long Q23;
    private Long Q24;
    private String Diagnosis;

    public DiagnosisFormResource toResource(DiagnosisForm diagnosisForm) {
        DiagnosisFormResource diagnosisFormResource = new DiagnosisFormResource();
        diagnosisFormResource.setId(diagnosisForm.getId());
        diagnosisFormResource.setQ1(diagnosisForm.getQ1());
        diagnosisFormResource.setQ2(diagnosisForm.getQ2());
        diagnosisFormResource.setQ3(diagnosisForm.getQ3());
        diagnosisFormResource.setQ4(diagnosisForm.getQ4());
        diagnosisFormResource.setQ5(diagnosisForm.getQ5());
        diagnosisFormResource.setQ6(diagnosisForm.getQ6());
        diagnosisFormResource.setQ7(diagnosisForm.getQ7());
        diagnosisFormResource.setQ8(diagnosisForm.getQ8());
        diagnosisFormResource.setQ9(diagnosisForm.getQ9());
        diagnosisFormResource.setQ10(diagnosisForm.getQ10());
        diagnosisFormResource.setQ11(diagnosisForm.getQ11());
        diagnosisFormResource.setQ12(diagnosisForm.getQ12());
        diagnosisFormResource.setQ13(diagnosisForm.getQ13());
        diagnosisFormResource.setQ14(diagnosisForm.getQ14());
        diagnosisFormResource.setQ15(diagnosisForm.getQ15());
        diagnosisFormResource.setQ16(diagnosisForm.getQ16());
        diagnosisFormResource.setQ17(diagnosisForm.getQ17());
        diagnosisFormResource.setQ18(diagnosisForm.getQ18());
        diagnosisFormResource.setQ19(diagnosisForm.getQ19());
        diagnosisFormResource.setQ20(diagnosisForm.getQ20());
        diagnosisFormResource.setQ21(diagnosisForm.getQ21());
        diagnosisFormResource.setQ22(diagnosisForm.getQ22());
        diagnosisFormResource.setQ23(diagnosisForm.getQ23());
        diagnosisFormResource.setQ24(diagnosisForm.getQ24());
        diagnosisFormResource.setDiagnosis(diagnosisForm.getDiagnosis());
        return diagnosisFormResource;
    }

    public DiagnosisForm toDiagnosisForm() {
        DiagnosisForm diagnosisForm = new DiagnosisForm();
        diagnosisForm.setId(this.getId());
        diagnosisForm.setQ1(this.getQ1());
        diagnosisForm.setQ2(this.getQ2());
        diagnosisForm.setQ3(this.getQ3());
        diagnosisForm.setQ4(this.getQ4());
        diagnosisForm.setQ5(this.getQ5());
        diagnosisForm.setQ6(this.getQ6());
        diagnosisForm.setQ7(this.getQ7());
        diagnosisForm.setQ8(this.getQ8());
        diagnosisForm.setQ9(this.getQ9());
        diagnosisForm.setQ10(this.getQ10());
        diagnosisForm.setQ11(this.getQ11());
        diagnosisForm.setQ12(this.getQ12());
        diagnosisForm.setQ13(this.getQ13());
        diagnosisForm.setQ14(this.getQ14());
        diagnosisForm.setQ15(this.getQ15());
        diagnosisForm.setQ16(this.getQ16());
        diagnosisForm.setQ17(this.getQ17());
        diagnosisForm.setQ18(this.getQ18());
        diagnosisForm.setQ19(this.getQ19());
        diagnosisForm.setQ20(this.getQ20());
        diagnosisForm.setQ21(this.getQ21());
        diagnosisForm.setQ22(this.getQ22());
        diagnosisForm.setQ23(this.getQ23());
        diagnosisForm.setQ24(this.getQ24());
        diagnosisForm.setDiagnosis(this.getDiagnosis());

        return diagnosisForm;
    }

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
