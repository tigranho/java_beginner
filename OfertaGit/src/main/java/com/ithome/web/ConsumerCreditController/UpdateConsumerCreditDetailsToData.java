package com.ithome.web.ConsumerCreditController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.ConsumerCreditDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.ConsumerCredit;
import com.ithome.web.Bean.Currancy;
import com.ithome.web.Helpers.AdminChecker;
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

@WebServlet("/UpdateConsumerCreditDetailsToData")
public class UpdateConsumerCreditDetailsToData extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private ConsumerCredit consumerCredit = new ConsumerCredit();
    private ConsumerCreditDaoController consumerCreditDaoController= new ConsumerCreditDaoController();
    private List<ConsumerCredit> consumerCreditList = new ArrayList<>();

    private int ProductCode = 0;
    private String BankName = null;
    private String ProductName = null;
    private int CoCMinAmount = 0;
    private int CoCMaxAmount = 0;
    private double CoCMinCredit = 0;
    private double CoCMaxCredit = 0;
    private double CoCPerFactual = 0;
    private String currancy = null;
    private int CoCMinPeriodMonths = 0;
    private int CoCMaxPeriodMonth = 0;
    private int CoCMinServiceFee = 0;
    private int CoCMaxServiceFee = 0;
    private int CoCMinAge = 0;
    private int CoCMaxAge = 0;
    private int Bankid = 0;
    private String bankWebSite = null;
    private String BankLink = null;
    private int CCId = 0;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateConsumerCreditDetailsToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateConsumerCreditDetailsToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateConsumerCreditDetailsToData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankDetailByBankName(BankName);
        getBankFullDetail();
        getCurrancyFullDetail();
        updteConsumerCreditInData(CreateNewConsumerCredit(), request, response, CCId);
    }

    private void updteConsumerCreditInData(int ConsumerCreditData, HttpServletRequest request, HttpServletResponse response, int CCId) throws ServletException, IOException, SQLException {
        if (ConsumerCreditData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getConsumerCreditById(CCId);
            setRequestToConsumerCreditUpdatePage(request);
            goBackToErrorPage(request, response, message);
        } else {
            String message = "Հաջողությամբ Թարմացվրիք սպառողական վարկ";
            getConsumerCreditById(CCId);
            setRequestToConsumerCreditUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateConsumerCredit.jsp").forward(request, response);
    }

    private void setRequestToConsumerCreditUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("consumerCreditList", consumerCreditList);
    }

    private void getConsumerCreditById(int clId) throws SQLException {
        consumerCreditList = consumerCreditDaoController.getConsumerCreditById(clId);
    }



    private int CreateNewConsumerCredit() throws SQLException {
        return consumerCreditDaoController.updateConsumerCredit(prepaireConsumerCreditDetailInData(),CCId);
    }

    private ConsumerCredit prepaireConsumerCreditDetailInData() {
        return new ConsumerCredit(ProductCode,Bankid,BankName,bankWebSite,CoCMinAge,CoCMaxAge,currancy,ProductName,
                CoCMinAmount,CoCMaxAmount,CoCMinCredit,CoCMaxCredit,CoCPerFactual,CoCMinPeriodMonths,CoCMaxPeriodMonth,CoCMaxServiceFee,CoCMinServiceFee,BankLink);

    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }


    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }


    private void getBankDetailByBankName(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        for (int i = 0; i < banksList.size(); i++) {
            Bankid = banksList.get(i).getBankId();
            bankWebSite = banksList.get(i).getBankWebSite();
        }
    }

    private void getParameters(HttpServletRequest request) {
        CCId = Integer.parseInt(request.getParameter("CLId"));
        ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        CoCMinAmount = Integer.parseInt(request.getParameter("CoCMinAmount"));
        CoCMaxAmount = Integer.parseInt(request.getParameter("CoCMaxAmount"));
        CoCMinCredit = Double.parseDouble(request.getParameter("CoCMinCredit"));
        CoCMaxCredit = Double.parseDouble(request.getParameter("CoCMaxCredit"));
        CoCPerFactual = Double.parseDouble(request.getParameter("CoCPerFactual"));
        currancy = request.getParameter("currancy");
        CoCMinPeriodMonths = Integer.parseInt(request.getParameter("CoCMinPeriodMonths"));
        CoCMaxPeriodMonth = Integer.parseInt(request.getParameter("CoCMaxPeriodMonth"));
        CoCMinServiceFee = Integer.parseInt(request.getParameter("CoCMinServiceFee"));
        CoCMaxServiceFee = Integer.parseInt(request.getParameter("CoCMaxServiceFee"));
        CoCMinAge = Integer.parseInt(request.getParameter("CoCMinAge"));
        CoCMaxAge = Integer.parseInt(request.getParameter("CoCMaxAge"));
        BankLink = request.getParameter("BankLink");
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




