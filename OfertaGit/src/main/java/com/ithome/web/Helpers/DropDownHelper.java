package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.DropDownController;
import com.ithome.web.Bean.DropDowns;

import java.sql.SQLException;
import java.util.List;


public class DropDownHelper {

    private DropDownController dropDownController = new DropDownController();
    private String[] PageNames = {"ԳԼԽԱՎՈՐ", "ԱՎԱՆԴՆԵՐ", "ՀԻՓՈԹԵՔ", "ՍՊԱՌՈՂԱԿԱՆ", "ԱՎՏՈՎԱՐԿ", "ՄԻԿՐՈՎԱՐԿ", "ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ"};

    public List<DropDowns> getPageName(String pageName) throws SQLException {
        switch (pageName) {
            case "ԳԼԽԱՎՈՐ":
                return dropDownController.getDropDownsByPosition(PageNames[0]);
            case "ԱՎԱՆԴՆԵՐ":
                return dropDownController.getDropDownsByPosition(PageNames[1]);
            case "ՀԻՓՈԹԵՔ":
                return dropDownController.getDropDownsByPosition(PageNames[2]);
            case "Հիփոթեք":
                return dropDownController.getDropDownsByPosition(PageNames[2]);
            case "ՍՊԱՌՈՂԱԿԱՆ":
                return dropDownController.getDropDownsByPosition(PageNames[3]);
            case "Սպարողական":
                return dropDownController.getDropDownsByPosition(PageNames[3]);
            case "ԱՎՏՈՎԱՐԿ":
                return dropDownController.getDropDownsByPosition(PageNames[4]);
            case "ՄԻԿՐՈՎԱՐԿ":
                return dropDownController.getDropDownsByPosition(PageNames[5]);
            case "ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ":
                return dropDownController.getDropDownsByPosition(PageNames[6]);
            default:
                return dropDownController.getDropDownsByPosition(PageNames[0]);
        }
    }

}
