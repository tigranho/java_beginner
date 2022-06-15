package com.ithome.web.Bean;

import java.sql.Date;
import java.time.LocalDate;

public class UsefulArticles {

    private int UAId;
    private String UATitleAm;
    private String UATitleEn;
    private String UATitleRu;
    private String UAMainTextAm;
    private String UAMainTextEn;
    private String UAMainTextRu;
    private String UAImageLink;
    private Date date;

    public UsefulArticles(int UAId, String UATitleAm, String UATitleEn, String UATitleRu, String UAMainTextAm, String UAMainTextEn, String UAMainTextRu, String UAImageLink) {
        this.UAId = UAId;
        this.UATitleAm = UATitleAm;
        this.UATitleEn = UATitleEn;
        this.UATitleRu = UATitleRu;
        this.UAMainTextAm = UAMainTextAm;
        this.UAMainTextEn = UAMainTextEn;
        this.UAMainTextRu = UAMainTextRu;
        this.UAImageLink = UAImageLink;
    }

    public UsefulArticles(String UAImageLink) {
        this.UAImageLink = UAImageLink;
    }

    public UsefulArticles(String UATitleAm, String UATitleEn, String UATitleRu, String UAMainTextAm, String UAMainTextEn, String UAMainTextRu, String UAImageLink) {
        this.UATitleAm = UATitleAm;
        this.UATitleEn = UATitleEn;
        this.UATitleRu = UATitleRu;
        this.UAMainTextAm = UAMainTextAm;
        this.UAMainTextEn = UAMainTextEn;
        this.UAMainTextRu = UAMainTextRu;
        this.UAImageLink = UAImageLink;
    }

    public UsefulArticles(String UATitleAm, String UATitleEn, String UATitleRu, String UAMainTextAm, String UAMainTextEn, String UAMainTextRu, Date date) {
        this.UATitleAm = UATitleAm;
        this.UATitleEn = UATitleEn;
        this.UATitleRu = UATitleRu;
        this.UAMainTextAm = UAMainTextAm;
        this.UAMainTextEn = UAMainTextEn;
        this.UAMainTextRu = UAMainTextRu;
        this.date=date;
    }

    public UsefulArticles() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUAId() {
        return UAId;
    }

    public void setUAId(int UAId) {
        this.UAId = UAId;
    }

    public String getUATitleAm() {
        return UATitleAm;
    }

    public void setUATitleAm(String UATitleAm) {
        this.UATitleAm = UATitleAm;
    }

    public String getUATitleEn() {
        return UATitleEn;
    }

    public void setUATitleEn(String UATitleEn) {
        this.UATitleEn = UATitleEn;
    }

    public String getUATitleRu() {
        return UATitleRu;
    }

    public void setUATitleRu(String UATitleRu) {
        this.UATitleRu = UATitleRu;
    }

    public String getUAMainTextAm() {
        return UAMainTextAm;
    }

    public void setUAMainTextAm(String UAMainTextAm) {
        this.UAMainTextAm = UAMainTextAm;
    }

    public String getUAMainTextEn() {
        return UAMainTextEn;
    }

    public void setUAMainTextEn(String UAMainTextEn) {
        this.UAMainTextEn = UAMainTextEn;
    }

    public String getUAMainTextRu() {
        return UAMainTextRu;
    }

    public void setUAMainTextRu(String UAMainTextRu) {
        this.UAMainTextRu = UAMainTextRu;
    }

    public String getUAImageLink() {
        return UAImageLink;
    }

    public void setUAImageLink(String UAImageLink) {
        this.UAImageLink = UAImageLink;
    }
}
