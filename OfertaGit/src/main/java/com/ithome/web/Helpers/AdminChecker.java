package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.AdminDaoController;
import com.ithome.web.Bean.Admin;

import java.util.List;

public class AdminChecker {

    private AdminDaoController adminDaoController = new AdminDaoController();
    public int getAdminId(String username) {
        System.out.println("passed getAdminId ");
        return adminDaoController.getAdminIdByUserName(username);
    }

    public List<Admin> getAllInfoofAdmin(int adminId) {
        System.out.println("passed getAllInfoofAdmin ");
        return adminDaoController.getAllAdminInfo(adminId);
    }

    private int getThePinCode(int adminId){
        return adminDaoController.getAdminpinCode(adminId);
    }

    public boolean checkTheCode(int pinCode, int adminId) {
        return pinCode == getThePinCode(adminId);
    }
}
