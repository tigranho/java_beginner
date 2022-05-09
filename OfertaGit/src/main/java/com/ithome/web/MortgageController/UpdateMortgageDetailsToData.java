package com.ithome.web.MortgageController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.AdminDao.PrepaimentOptionDao;
import com.ithome.web.Bean.*;
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

@WebServlet("/UpdateMortgageDetailsToData")
public class UpdateMortgageDetailsToData extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    private List<Banks> banksList = new ArrayList<>();
    private List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private List<RePaymentOptions> rePaymentOptionsList = new ArrayList<>();
    private PrepaimentOptionDao prepaimentOptionDao = new PrepaimentOptionDao();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();
    private List<Mortgage> mortgageList = new ArrayList<>();

    private int ProductCode = 0;
    private String BankName = null;
    private String ProductName = null;
    private int MMinAmount = 0;
    private int MMaxAmount = 0;
    private double MMinLoan = 0;
    private double MMaxLoan = 0;
    private double MFatual = 0;
    private int MMinDownPayment = 0;
    private int MMaxDownPayment = 0;
    private String currancy = null;
    private int MMinPeriodMonth = 0;
    private int MMaxPeriodMonth = 0;
    private int MMinServiceFee = 0;
    private int MMaxServiceFee = 0;
    private int minAge = 0;
    private int maxAge = 0;
    private int Bankid = 0;
    private String bankWebSite = null;
    private String RePaymentOptions =null;
    private int prepirmentid =0;
    private int MId=0;
    private String BankLink=null;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMortgageDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMortgageDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMortgageDetailsToData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankDetailByBankName(BankName);
        getBankFullDetail();
        getCurrancyFullDetail();
        getRePrepaimentFullDetail();
        getRepaymentIdByName(RePaymentOptions);
        updateMortageInData(createNewMortageInData(),request,response,MId);
    }

    private void updateMortageInData(int newMortageInData, HttpServletRequest request, HttpServletResponse response, int clId) throws ServletException, IOException, SQLException {
        if (newMortageInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getMortgageDetailById(clId);
            setRequestToMortgageUpdatePage(request);
            goBackToErrorPage(request, response, message);
        } else {
            String message = "Հաջողությամբ թարմացվեց Հիփոթեքը վարկը";
            getMortgageDetailById(MId);
            setRequestToMortgageUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateMortgage.jsp").forward(request, response);
    }

    private void setRequestToMortgageUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("MortgageList", mortgageList);
    }
    private void getRepaymentIdByName(String rePaymentOptions) throws SQLException {
        rePaymentOptionsList = prepaimentOptionDao.getRePaymentOptionsByArmenianName(rePaymentOptions);
        for (int i = 0; i <rePaymentOptionsList.size() ; i++) {
            prepirmentid = rePaymentOptionsList.get(i).getRePrepaimentOptionId();
        }

    }


    private void getMortgageDetailById(int clId) throws SQLException {
        mortgageList = mortgageDaoController.getMortagesById(clId);
    }

    private int createNewMortageInData() throws SQLException {
        return mortgageDaoController.updateMortgageInData(prepaireMortgageForData(),MId);
    }

    private Mortgage prepaireMortgageForData() {
        return new Mortgage(ProductCode,Bankid,BankName,bankWebSite,minAge,maxAge,currancy,ProductName,MMinAmount
                ,MMaxAmount,MMinLoan,MMaxLoan,MFatual,MMinDownPayment,MMaxDownPayment,MMinPeriodMonth,MMaxPeriodMonth,MMinServiceFee,MMaxServiceFee,
                prepirmentid,RePaymentOptions,BankLink);
    }

    private void getRePrepaimentFullDetail() throws SQLException {
        rePaymentOptionsList = prepaimentOptionDao.GetAllRePaymentOption();
    }

    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }

    private void getBankDetailByBankName(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        for (int i = 0; i <banksList.size() ; i++) {
            Bankid = banksList.get(i).getBankId();
            bankWebSite = banksList.get(i).getBankWebSite();
        }
    }


    private void getParameters(HttpServletRequest request) {
        MId = Integer.parseInt(request.getParameter("MId"));
        ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("productName");
        MMinAmount = Integer.parseInt(request.getParameter("MMinAmount"));
        MMaxAmount = Integer.parseInt(request.getParameter("MMaxAmount"));
        MMinLoan = Double.parseDouble(request.getParameter("MMinLoan"));
        MMaxLoan = Double.parseDouble(request.getParameter("MMaxLoan"));
        MFatual = Double.parseDouble(request.getParameter("MFatual"));
        MMinDownPayment = Integer.parseInt(request.getParameter("MMinxDownPayment"));
        MMaxDownPayment = Integer.parseInt(request.getParameter("MMaxDownPayment"));
        currancy = request.getParameter("currancy");
        MMinPeriodMonth = Integer.parseInt(request.getParameter("MMinPeriodMonth"));
        MMaxPeriodMonth = Integer.parseInt(request.getParameter("MMaxPeriodMonth"));
        MMinServiceFee = Integer.parseInt(request.getParameter("MMinServiceFee"));
        MMaxServiceFee = Integer.parseInt(request.getParameter("MMaxServiceFee"));
        minAge = Integer.parseInt(request.getParameter("minAge"));
        maxAge = Integer.parseInt(request.getParameter("maxAge"));
        RePaymentOptions= request.getParameter("MRepayment");
        BankLink= request.getParameter("BankLink");
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

