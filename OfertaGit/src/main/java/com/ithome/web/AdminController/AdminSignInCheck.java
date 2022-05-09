package com.ithome.web.AdminController;

import com.ithome.web.AdminDao.AdminDaoController;
import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CounterDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.HitCounterBean;
import com.ithome.web.Helpers.GetBankFullInfo;
import com.ithome.web.counterController.HitCounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AdminSignInCheck")
public class AdminSignInCheck extends HttpServlet {
    private String username = null;
    private String password = null;
    private AdminDaoController adminDaoController = new AdminDaoController();
    private int adminId = 0;
    private List<Admin> adminFullInfo = new ArrayList<>();
    private List<Banks> banksList = new ArrayList<>();
    private List<HitCounterBean> mustVisitedPages = new ArrayList<>();
    private CounterDaoController counterDaoController = new CounterDaoController();
    private GetBankFullInfo bf = new GetBankFullInfo();

    private int countMainPage = 0;
    private int countContactUs = 0;
    private int countAboutUs = 0;
    private int countPartners = 0;
    private int countBlog = 0;
    private int CountAmd = 0;
    private int CountRub = 0;
    private int CountEuro = 0;
    private int CountUSD = 0;
    private List<String> countries = new ArrayList<>();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            adminSignInCheck(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            adminSignInCheck(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adminSignInCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");

        if (checkAdminImputes(request)) {
            //getParameters(request);
            getAdminId(username);
            getSession(request);
            getAdminFullInfoInList(adminId);
            getBankFullInfo();
            CountPages(getMustVisitedPages());
            CountCurrancy(getMustVisitedPages());
            // getVisitedCountries(getMustVisitedPages());
            setAttributesForPage(request);
            gotoMasterAdminPage(request, response);

        } else {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, կրկին փորձեք";
            sendToLoginPage(request, response, message);
        }
    }

    private void getVisitedCountries(List<HitCounterBean> mustVisitedPages) {
        countries.add(mustVisitedPages.get(0).getCity());
        String countyArray = mustVisitedPages.get(0).getCity();
        for (int i = 0; i < mustVisitedPages.size(); i++) {
            if (countyArray.equals(mustVisitedPages.get(i).getCity())) {
                continue;
            } else {
                countries.add(mustVisitedPages.get(i).getCity());
            }
        }
    }

    private void CountCurrancy(List<HitCounterBean> mustVisitedPages) {
        for (int i = 0; i < mustVisitedPages.size(); i++) {
            if (isEqualCurrancy(i, mustVisitedPages, "AMD")) {
                addCountToAMD();
            } else if (isEqualCurrancy(i, mustVisitedPages, "RUB")) {
                addCountToRUB();
            } else if (isEqualCurrancy(i, mustVisitedPages, "USD")) {
                addCountToUSD();
            } else {
                addCountToEURO();
            }
        }
    }

    private boolean isEqualCurrancy(int i, List<HitCounterBean> mustVisitedPages, String usd) {
        return mustVisitedPages.get(i).getPageCurrancy().equals(usd);
    }

    private void addCountToEURO() {
        CountEuro++;
    }

    private void addCountToUSD() {
        CountUSD++;
    }

    private void addCountToRUB() {
        CountRub++;
    }

    private void addCountToAMD() {
        CountAmd++;
    }


    private void CountPages(List<HitCounterBean> mustVisitedPages) {
        if(mustVisitedPages.size()>0) {
            for (int i = 0; i < mustVisitedPages.size(); i++) {
                if (isEqual(i, mustVisitedPages, "Գլխաոր")) {
                    addCountToMainPage();
                } else if (isEqual(i, mustVisitedPages, "Կապ մեզ հետ")) {
                    addCountToContuctUs();
                } else if (isEqual(i, mustVisitedPages, "Մեր մասին")) {
                    addCountToAboutUs();
                } else if (isEqual(i, mustVisitedPages, "Գործնկերներ")) {
                    addCountToPartners();
                } else {
                    addCountToElse();
                }
            }
        }
        //System.out.println(mustVisitedPages.get(0).getName());

    }

    private void addCountToElse() {
        countBlog++;
    }

    private void addCountToPartners() {
        countPartners++;
    }

    private void addCountToAboutUs() {
        countAboutUs++;
    }

    private void addCountToContuctUs() {
        countContactUs++;
    }

    private void addCountToMainPage() {
        countMainPage++;
    }

    private boolean isEqual(int i, List<HitCounterBean> mustVisitedPages, String pageName) {
        return mustVisitedPages.get(i).getName().equals(pageName);
    }

    private List<HitCounterBean> getMustVisitedPages() throws SQLException {
        return counterDaoController.getCountersDetail();
    }

    private void getBankFullInfo() throws SQLException {
        banksList = bf.contactBankDetail();
    }


    private void sendToLoginPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/SignIn.jsp").forward(request, response);
    }

    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("banksList", banksList);
        request.getRequestDispatcher("/WEB-INF/AdminApp.jsp").forward(request, response);
    }

    private void getAdminFullInfoInList(int adminId) {
        adminFullInfo = adminDaoController.getAllAdminInfo(adminId);
    }

    private void setAttributesForPage(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminFullInfo);
        request.setAttribute("countMainPage", countMainPage);
        request.setAttribute("countContactUs", countContactUs);
        request.setAttribute("countAboutUs", countAboutUs);
        request.setAttribute("countPartners", countPartners);
        request.setAttribute("countBlog", countBlog);
        request.setAttribute("CountEuro", CountEuro);
        request.setAttribute("CountUSD", CountUSD);
        request.setAttribute("CountRub", CountRub);
        request.setAttribute("CountAmd", CountAmd);
        request.setAttribute("countries", countries);
    }

    private int getAdminId(String username) {
        adminId = adminDaoController.getAdminIdByUserName(username);
        return adminId;
    }

    private void getSession(HttpServletRequest request) {
        String AdminSession;
        HttpSession session = request.getSession();
        session.setAttribute("admins", username);
        AdminSession = (String) session.getAttribute("admins");
        System.out.println(AdminSession);
    }

    private boolean checkAdminImputes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("admins"));
        if (session.getAttribute("admins") != null) {
            return true;
        }
        getParameters(request);
        return adminDaoController.validateAdminUsers(username, password);
    }

    private void getParameters(HttpServletRequest request) {

        username = request.getParameter("username");
        password = request.getParameter("password");

    }

    private boolean checkAdminSession(HttpSession session) {
        return session.getAttribute("admin") != null;
    }

}
