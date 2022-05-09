package com.ithome.web.Bean;


public class AboutUs {

    private int aboutId;
    private String about_arm;
    private String about_Ru;
    private String about_Eng;

    public AboutUs(String about_arm) {
        this.about_arm = about_arm;
    }

    public AboutUs(int aboutId, String about_arm, String about_Ru, String about_Eng) {
        this.aboutId = aboutId;
        this.about_arm = about_arm;
        this.about_Ru = about_Ru;
        this.about_Eng = about_Eng;
    }

    public AboutUs(String about_arm, String about_Ru, String about_Eng) {
        this.about_arm = about_arm;
        this.about_Ru = about_Ru;
        this.about_Eng = about_Eng;
    }

    public int getAboutId() {
        return aboutId;
    }

    public AboutUs() {
    }

    public void setAboutId(int aboutId) {
        this.aboutId = aboutId;
    }

    public String getAbout_arm() {
        return about_arm;
    }

    public void setAbout_arm(String about_arm) {
        this.about_arm = about_arm;
    }

    public String getAbout_Ru() {
        return about_Ru;
    }

    public void setAbout_Ru(String about_Ru) {
        this.about_Ru = about_Ru;
    }

    public String getAbout_Eng() {
        return about_Eng;
    }

    public void setAbout_Eng(String about_Eng) {
        this.about_Eng = about_Eng;
    }
}
