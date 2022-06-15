package com.ithome.web.MortgageController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.AdminDao.PrepaimentOptionDao;
import com.ithome.web.AdminDao.ProductNameDaoController;
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
import java.util.Random;

@WebServlet("/AddNewMortgageToData")
public class AddNewMortgageToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    private Mortgage mortgage = new Mortgage();
    private List<Mortgage> mortgageList = new ArrayList<>();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();
    private PrepaimentOptionDao prepaimentOptionDao = new PrepaimentOptionDao();
    private ProductNameDaoController productNameDaoController = new ProductNameDaoController();
    private ProductName productName = new ProductName();

    private int ProductCode = 0;
    private String BankName = null;
    private String ProductName = null;
    private String ProductNameEn = null;
    private String ProductNameRu = null;
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
    private List<RePaymentOptions> rePaymentOptionsList = new ArrayList<>();
    private String Prepaiment =null;
    private String BankLink =null;
    private int bankCode =0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewMortgageToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewMortgageToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewMortgageToData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankInfoFromBankname(BankName);
        getRepaymentIdByName(RePaymentOptions);
        createProductCode();
        checkIfProductNameAdded(createProductNameInData(),request,response);
        checkIfCardAdded(createNewMortgageInData(), request, response);
    }

    private void createProductCode() {
        Random random = new Random();
        bankCode =getBankCode();
        ProductCode = Integer.parseInt("" + bankCode + (random.nextInt(10000)+1000));
    }

    private int getBankCode() {
        for (int i = 0; i <banksList.size() ; i++) {
            bankCode=  banksList.get(i).getBankCode();
        }
        return bankCode;
    }

    private void checkIfProductNameAdded(int productNameInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(productNameInData == 0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ անվանման մեջ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        }
    }

    private int createProductNameInData() throws SQLException {
        return productNameDaoController.AddNewProductName(PrepaireProductNames());
    }

    private com.ithome.web.Bean.ProductName PrepaireProductNames() {
        return new ProductName(ProductCode,ProductName,ProductNameEn,ProductNameRu);
    }


    private void checkIfCardAdded(int newMortgageInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (newMortgageInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {
            setRequestToPage(request);
            gotoNextPage(request,response);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddNewMComments.jsp").forward(request, response);
    }

    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewMortgage.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", ProductCode);
    }

    private void getRepaymentIdByName(String rePaymentOptions) throws SQLException {
        rePaymentOptionsList = prepaimentOptionDao.getRePaymentOptionsByArmenianName(rePaymentOptions);
        for (int i = 0; i <rePaymentOptionsList.size() ; i++) {
            prepirmentid = rePaymentOptionsList.get(i).getRePrepaimentOptionId();
        }

    }

    private int createNewMortgageInData() throws SQLException {
        return mortgageDaoController.AddNewMortgage(createNewMortgageForData());

    }

    private Mortgage createNewMortgageForData() {
        return new Mortgage(ProductCode,Bankid,BankName,bankWebSite,minAge,maxAge,currancy,ProductName,MMinAmount
        ,MMaxAmount,MMinLoan,MMaxLoan,MFatual,MMinDownPayment,MMaxDownPayment,MMinPeriodMonth,MMaxPeriodMonth,MMinServiceFee,MMaxServiceFee,
                prepirmentid,RePaymentOptions,BankLink);
    }

    private void getBankInfoFromBankname(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        for (int i = 0; i < banksList.size(); i++) {
            Bankid = banksList.get(i).getBankId();
            bankWebSite = banksList.get(i).getBankWebSite();
        }
    }

    private void getParameters(HttpServletRequest request) {
     /*   ProductCode = Integer.parseInt(request.getParameter("ProductCode"));*/
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        ProductNameEn = request.getParameter("ProductNameEng");
        ProductNameRu = request.getParameter("ProductNameRus");
        MMinAmount = Integer.parseInt(request.getParameter("MMinAmount"));
        MMaxAmount = Integer.parseInt(request.getParameter("MMaxAmount"));
        MMinLoan = Double.parseDouble(request.getParameter("MMinLoan"));
        MMaxLoan = Double.parseDouble(request.getParameter("MMaxLoan"));
        MFatual = Double.parseDouble(request.getParameter("MFatual"));
        MMinDownPayment = Integer.parseInt(request.getParameter("MMinDownPayment"));
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

