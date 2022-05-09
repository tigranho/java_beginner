package com.ithome.web.Bean;

public class Admin {
    private int adminid;
    private String username;
    private String password;
    private int adminpincode;

    public Admin(int adminid, String username, String password) {
        this.adminid = adminid;
        this.username = username;
        this.password = password;
    }

    public Admin(int adminid, String username, String password, int adminpincode) {
        this.adminid = adminid;
        this.username = username;
        this.password = password;
        this.adminpincode = adminpincode;
    }

    public Admin(String username, String password, int adminpincode) {
        this.username = username;
        this.password = password;
        this.adminpincode = adminpincode;
    }

    public Admin(int adminpincode) {
        this.adminpincode = adminpincode;
    }

    public int getAdminpincode() {
        return adminpincode;
    }

    public void setAdminpincode(int adminpincode) {
        this.adminpincode = adminpincode;
    }

    public Admin() {
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
