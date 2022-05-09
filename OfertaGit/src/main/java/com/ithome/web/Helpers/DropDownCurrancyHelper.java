package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.DropDownController;
import com.ithome.web.Bean.DropDowns;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DropDownCurrancyHelper {

    private int id = 0;
    private DropDownController dropDownController = new DropDownController();

    private void getDropDownByCurrancy(List<DropDowns> dropList, String currancy) {
        for (int i = 0; i < dropList.size(); i++) {
            if (dropList.get(i).getCurrancy().equals(currancy)) {
                id  = dropList.get(i).getId();
            }
        }
    }

    public List<DropDowns> getDropDownWithCurrancy(List<DropDowns> dropList, String currancy) throws SQLException {
        getDropDownByCurrancy(dropList,currancy);
        return dropDownController.getDropDownsById(id);
    }
}

