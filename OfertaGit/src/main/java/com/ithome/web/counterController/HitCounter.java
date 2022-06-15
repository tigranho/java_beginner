package com.ithome.web.counterController;


import com.ithome.web.AdminDao.CounterDaoController;
import com.ithome.web.Bean.HitCounterBean;
import com.ithome.web.Helpers.PageNameHelper;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HitCounter extends HttpServlet {
    private CounterDaoController counterDaoController = new CounterDaoController();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private String PPageName = null;
    private String PCurancy = null;
    private String PCity = null;
    private String PLang = null;
    private String PsessionId = null;
    private Date date = new Date();

    public void countingHits(String pageName, String pageCurrancy, String city, String language, String sessionId) throws SQLException {
        PPageName = checkPageName(pageName);
        PCurancy = pageCurrancy;
        PCity = city;
        PLang = language;
        PsessionId = sessionId;
        addCounterToData(createNewCounter());
    }


    private String checkPageName(String pageName) {
       return pageNameHelper.ArmPageName(pageName);
    }

    private void addCounterToData(int counter) {
        if (counter == 0) {
            System.out.println("counter not added ");
        }else{
            System.out.println("counter added");
        }
    }

    private int createNewCounter() throws SQLException {
        return counterDaoController.Addcounter(ObjectToData());
    }

    private HitCounterBean ObjectToData() {
        return new HitCounterBean(PPageName,PCurancy,PCity,PLang,date,PsessionId);
    }
}
