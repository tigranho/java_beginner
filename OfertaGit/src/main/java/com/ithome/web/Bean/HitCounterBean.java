package com.ithome.web.Bean;

import java.util.Date;

public class HitCounterBean {
    private int id;
    private String name;
    private String pageCurrancy;
    private String city;
    private String pageLanguage;
    private Date dateandtime;
    private String sessionId;

    public HitCounterBean(String Name, String pageCurrancy, String city, String pageLanguage, Date dateandtime, String sessionId) {
        this.name = Name;
        this.pageCurrancy = pageCurrancy;
        this.city = city;
        this.pageLanguage = pageLanguage;
        this.dateandtime = dateandtime;
        this.sessionId = sessionId;
    }

    public HitCounterBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageCurrancy() {
        return pageCurrancy;
    }

    public void setPageCurrancy(String pageCurrancy) {
        this.pageCurrancy = pageCurrancy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPageLanguage() {
        return pageLanguage;
    }

    public void setPageLanguage(String pageLanguage) {
        this.pageLanguage = pageLanguage;
    }

    public Date getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(Date dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}