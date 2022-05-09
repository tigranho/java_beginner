package com.ithome.web.MarketingController;

import com.ithome.web.AdminDao.ConsumerCreditDaoController;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.ConsumerCredit;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateConsumerLoanApperanceInData")
public class UpdateConsumerLoanApperanceInData extends HttpServlet {
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
    private List<ConsumerCredit> consumerCreditList = new ArrayList<>();
    private ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateConsumerLoanApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateConsumerLoanApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateConsumerLoanApperanceInData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getBankFullInfo();
        getParameters(request);
        getAllConsumerInfoByBankIdAndOrder();
        checkAppearance(request, response);
        //checkApperanceOldData();
        setRequestToPage(request);
        gotoPage(request,response);

    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("ListMarketing", consumerCreditList);
        request.setAttribute("banksList", banksList);
    }

    private void checkApperanceOldData() throws SQLException {
        for (int i = 0; i < consumerCreditList.size(); i++) {
            if (consumerCreditList.get(i).getOrderOfAppearance() == AppearanceNumber) {
                if(consumerCreditList.get(i).getProductCode()!= productCode) {
                    getProductCode = consumerCreditList.get(i).getProductCode();
                    updateConsumerInDataNewNumber(UpdateAppearanceInDataNewNumberSS());
                }
            }

        }
    }

    private boolean updateConsumerInDataNewNumber(int updateAppearanceInDataNewNumberSS) {
        return updateAppearanceInDataNewNumberSS == 0;
    }

    private int UpdateAppearanceInDataNewNumberSS() throws SQLException {
        return consumerCreditDaoController.UpdateConsumerMarketingInData(prepaireMortgageInfoForNewDataSS(),getProductCode);
    }

    private ConsumerCredit prepaireMortgageInfoForNewDataSS() {
        return new ConsumerCredit(getAppearanceNumber, SpecialOffer, FirstSearchList, SendRequest, GotoPage);
    }

    private void checkAppearance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        for (int i = 0; i < consumerCreditList.size(); i++) {
            if (consumerCreditList.get(i).getProductCode() == productCode) {
                getAppearanceNumber = consumerCreditList.get(i).getOrderOfAppearance();
                getProductCode = consumerCreditList.get(i).getProductCode();
                SpecialOffer = consumerCreditList.get(i).getSpecialOffer();
                FirstSearchList = consumerCreditList.get(i).getFirstSearchList();
                SendRequest = consumerCreditList.get(i).getSendRequest();
                GotoPage = consumerCreditList.get(i).getGotoPage();
                updateConsumerInDataFS(UpdateAppearanceInDataFS(), request, response);
            }

        }
    }

    private void updateConsumerInDataFS(int updateAppearanceInDataFS, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (updateAppearanceInDataFS == 0) {
            gotoPage(request,response);
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowConsumerCreditListMarketing.jsp").forward(request,response);

    }

    private int UpdateAppearanceInDataFS() throws SQLException {
        return consumerCreditDaoController.UpdateConsumerMarketingInData(prepaireMortgageInfoForDataFS(), getProductCode);
    }

    private ConsumerCredit prepaireMortgageInfoForDataFS() {
        return new ConsumerCredit(AppearanceNumber, SpecialOffer, FirstSearchList, SendRequest, GotoPage);
    }

    private void getAllConsumerInfoByBankIdAndOrder() throws SQLException {
        consumerCreditList = consumerCreditDaoController.getConsumerCreditBybankId(bankId);
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





