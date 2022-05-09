package com.ithome.web.MarketingController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.GetBankFullInfo;
import com.ithome.web.Helpers.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateAGApperanceInData")
public class UpdateAGApperanceInData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int AppearanceNumber = 0;
    private int Id = 0;
    private int productCode = 0;
    private GetBankFullInfo bf = new GetBankFullInfo();
    private List<Banks> banksList = new ArrayList<>();
    private int getProductCode = 0;
    private int SpecialOffer = 0;
    private int FirstSearchList = 0;
    private int SendRequest = 0;
    private int GotoPage = 0;
    private int getAppearanceNumber = 0;
    private int bankId=0;
    private List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateAGApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateAGApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAGApperanceInData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getBankFullInfo();
        getParameters(request);
        getAllMrtgageInfoByBankIdAndOrder();
        checkAppearance(request, response);
        //checkApperanceOldData();
        setRequestToPage(request,response);
        gotoPage(request,response);
    }

    private void checkApperanceOldData() throws SQLException {
        for (int i = 0; i < agriculturalCreditList.size(); i++) {
            if (agriculturalCreditList.get(i).getOrderOfAppearance() == AppearanceNumber) {
                if(agriculturalCreditList.get(i).getProductCode()!= productCode) {
                    getProductCode = agriculturalCreditList.get(i).getProductCode();
                    updateAGInDataNewNumber(UpdateAppearanceInDataNewNumberSS());
                }
            }

        }
    }

    private boolean updateAGInDataNewNumber(int updateAppearanceInDataNewNumberSS) {
        return  updateAppearanceInDataNewNumberSS == 0;
    }

    private int UpdateAppearanceInDataNewNumberSS() throws SQLException {
        return agriculturalCreditDao.UpdateAGMarketingInData(prepaireAGInfoForNewDataSS(),getAppearanceNumber);
    }

    private AgriculturalCredit prepaireAGInfoForNewDataSS() {
        return new AgriculturalCredit(getAppearanceNumber, SpecialOffer, FirstSearchList, SendRequest, GotoPage);
    }


    private void checkAppearance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        for (int i = 0; i < agriculturalCreditList.size(); i++) {
            if (agriculturalCreditList.get(i).getProductCode() == productCode) {
                getAppearanceNumber = agriculturalCreditList.get(i).getOrderOfAppearance();
                getProductCode = agriculturalCreditList.get(i).getProductCode();
                SpecialOffer = agriculturalCreditList.get(i).getSpecialOffer();
                FirstSearchList = agriculturalCreditList.get(i).getFirstSearchList();
                SendRequest = agriculturalCreditList.get(i).getSendRequest();
                GotoPage = agriculturalCreditList.get(i).getGotoPage();
                updateAGInDataFS(UpdateAppearanceInDataFS(), request, response);
            }

        }
    }

    private void updateAGInDataFS(int updateAppearanceInDataFS, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (updateAppearanceInDataFS == 0) {
            gotoPage(request,response);
        }
    }
    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowAgricultureListMarketing.jsp").forward(request,response);
    }

    private void setRequestToPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("ListMarketing", agriculturalCreditList);
        request.setAttribute("banksList", banksList);
    }


    private int UpdateAppearanceInDataFS() throws SQLException {
        return agriculturalCreditDao.UpdateAGMarketingInData(prepaireMortgageInfoForDataFS(), getProductCode);
    }

    private AgriculturalCredit prepaireMortgageInfoForDataFS() {
        return new AgriculturalCredit(AppearanceNumber, SpecialOffer, FirstSearchList, SendRequest, GotoPage);
    }


    private void getAllMrtgageInfoByBankIdAndOrder() throws SQLException {
        agriculturalCreditList = agriculturalCreditDao.getAgriculturalCreditBybankId(bankId);
    }


    private void getParameters(HttpServletRequest request) {
        AppearanceNumber = Integer.parseInt(request.getParameter("AppearanceNumber"));
        Id = Integer.parseInt(request.getParameter("Id"));
        productCode = Integer.parseInt(request.getParameter("productCode"));
        bankId = Integer.parseInt(request.getParameter("bankId"));
    }

    private void getBankFullInfo() throws SQLException {
        banksList = bf.contactBankDetail();
    }


    /**
     * Controlling the session for admin using helper classes
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        getSession(session, request, response);
    }

    /**
     * Fill admin in list with the specific id
     *
     * @param adminid
     */
    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
    }

    /**
     * get admin admin id by username from session
     *
     * @param request
     * @param response
     */
    private void getAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        adminId = adminChecker.getAdminId(username);
        getFullAdminList(adminId);
    }

    /**
     * Session of uadmin if no session true send to login page else get the seesion key
     *
     * @param session
     * @param request
     * @param response
     * @throws IOException
     */
    private void getSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (checker.checkSession(request, response)) {
            username = checker.requestSessionofAdmin(session);
        } else {
            response.sendRedirect("/admin/SignIn.jsp");
        }
    }
}