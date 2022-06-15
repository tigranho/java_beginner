package com.ithome.web.Helpers;

public abstract class StringIntegerChecker {

    protected String returnString(String search) {
        return isNumeric(search) ? search : "-1";
    }

    protected boolean isNumeric(String strNum) {
        IsNull(strNum);
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    protected String IsNull(String strNum) {
        return strNum != null ? strNum : "Not Found";
    }
}
