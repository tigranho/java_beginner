package com.ithome.web.Bean;

public class Terms {
    private int termsId;
    private String termsAm;
    private String termsRu;
    private String termsEn;

    public Terms(int termsId, String termsAm, String termsRu, String termsEn) {
        this.termsId = termsId;
        this.termsAm = termsAm;
        this.termsRu = termsRu;
        this.termsEn = termsEn;
    }

    public Terms(String termsAm, String termsRu, String termsEn) {
        this.termsAm = termsAm;
        this.termsRu = termsRu;
        this.termsEn = termsEn;
    }

    public Terms() {
    }

    public int getTermsId() {
        return termsId;
    }

    public void setTermsId(int termsId) {
        this.termsId = termsId;
    }

    public String getTermsAm() {
        return termsAm;
    }

    public void setTermsAm(String termsAm) {
        this.termsAm = termsAm;
    }

    public String getTermsRu() {
        return termsRu;
    }

    public void setTermsRu(String termsRu) {
        this.termsRu = termsRu;
    }

    public String getTermsEn() {
        return termsEn;
    }

    public void setTermsEn(String termsEn) {
        this.termsEn = termsEn;
    }
}
