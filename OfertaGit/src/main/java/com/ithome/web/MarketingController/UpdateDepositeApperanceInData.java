package com.ithome.web.MarketingController;

import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Deposit;
import com.ithome.web.Bean.Mortgage;
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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateDepositeApperanceInData")
public class UpdateDepositeApperanceInData extends HttpServlet {
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
    private List<Deposit> depositList = new ArrayList<>();
    private DepositDaoController depositDaoController = new DepositDaoController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateDepositeApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateDepositeApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDepositeApperanceInData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getBankFullInfo();
        getParameters(request);
        getAllDepositeInfoByBankIdAndOrder();
        checkAppearance(request, response);
        //checkApperanceOldData();
        setRequestToPage(request);
        gotoPage(request,response);

    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("ListMarketing", depositList);
        request.setAttribute("banksList", banksList);
    }

    private void checkApperanceOldData() throws SQLException {
        for (int i = 0; i < depositList.size(); i++) {
            if (depositList.get(i).getOrderOfAppearance() == AppearanceNumber) {
                if(depositList.get(i).getProductCode()!= productCode) {
                    getProductCode = depositList.get(i).getProductCode();
                    updateDepositeInDataNewNumber(UpdateAppearanceInDataNewNumberSS());
                }
            }

        }
    }

    private boolean updateDepositeInDataNewNumber(int updateAppearanceInDataNewNumberSS) {
        return  updateAppearanceInDataNewNumberSS == 0;
    }

    private int UpdateAppearanceInDataNewNumberSS() throws SQLException {
        return depositDaoController.updateDepositeInData(prepaireMortgageInfoForNewDataSS(),getAppearanceNumber);
    }

    private Deposit prepaireMortgageInfoForNewDataSS() {
        return new Deposit(getAppearanceNumber, SpecialOffer, FirstSearchList, SendRequest, GotoPage);
    }


    private void checkAppearance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        for (int i = 0; i < depositList.size(); i++) {
            if (depositList.get(i).getProductCode() == productCode) {
                getAppearanceNumber = depositList.get(i).getOrderOfAppearance();
                getProductCode = depositList.get(i).getProductCode();
                SpecialOffer = depositList.get(i).getSpecialOffer();
                FirstSearchList = depositList.get(i).getFirstSearchList();
                SendRequest = depositList.get(i).getSendRequest();
                GotoPage = depositList.get(i).getGotoPage();
                updateDepositeInDataFS(UpdateAppearanceInDataFS(), request, response);
            }

        }
    }


    private int UpdateAppearanceInDataFS() throws SQLException {
        return depositDaoController.updateDepositeInData(prepaireDepositeInfoForDataFS(), getProductCode);
    }

    private Deposit prepaireDepositeInfoForDataFS() {
        return new Deposit(AppearanceNumber, SpecialOffer, FirstSearchList, SendRequest, GotoPage);
    }

    private void updateDepositeInDataFS(int updateAppearanceInDataFS, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (updateAppearanceInDataFS == 0) {
            gotoPage(request,response);
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowDepositListMarketing.jsp").forward(request,response);
    }


    private void getAllDepositeInfoByBankIdAndOrder() throws SQLException {
        depositList = depositDaoController.getDepositBybankId(bankId);
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






