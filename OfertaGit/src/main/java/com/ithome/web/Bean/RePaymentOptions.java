package com.ithome.web.Bean;

public class RePaymentOptions {
    private int rePrepaimentOptionId;
    private String rePrepaimentOptionAm;
    private String rePrepaimentOptionru;
    private String rePrepaimentOptionen;

    public RePaymentOptions(int rePrepaimentOptionId, String rePrepaimentOptionAm, String rePrepaimentOptionru, String rePrepaimentOptionen) {
        this.rePrepaimentOptionId = rePrepaimentOptionId;
        this.rePrepaimentOptionAm = rePrepaimentOptionAm;
        this.rePrepaimentOptionru = rePrepaimentOptionru;
        this.rePrepaimentOptionen = rePrepaimentOptionen;
    }

    public RePaymentOptions(String rePrepaimentOptionAm, String rePrepaimentOptionru, String rePrepaimentOptionen) {
        this.rePrepaimentOptionAm = rePrepaimentOptionAm;
        this.rePrepaimentOptionru = rePrepaimentOptionru;
        this.rePrepaimentOptionen = rePrepaimentOptionen;
    }

    public RePaymentOptions() {
    }

    public int getRePrepaimentOptionId() {
        return rePrepaimentOptionId;
    }

    public void setRePrepaimentOptionId(int rePrepaimentOptionId) {
        this.rePrepaimentOptionId = rePrepaimentOptionId;
    }

    public String getRePrepaimentOptionAm() {
        return rePrepaimentOptionAm;
    }

    public void setRePrepaimentOptionAm(String rePrepaimentOptionAm) {
        this.rePrepaimentOptionAm = rePrepaimentOptionAm;
    }

    public String getRePrepaimentOptionru() {
        return rePrepaimentOptionru;
    }

    public void setRePrepaimentOptionru(String rePrepaimentOptionru) {
        this.rePrepaimentOptionru = rePrepaimentOptionru;
    }

    public String getRePrepaimentOptionen() {
        return rePrepaimentOptionen;
    }

    public void setRePrepaimentOptionen(String rePrepaimentOptionen) {
        this.rePrepaimentOptionen = rePrepaimentOptionen;
    }
}
