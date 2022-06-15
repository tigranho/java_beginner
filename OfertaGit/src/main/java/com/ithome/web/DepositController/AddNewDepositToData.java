package com.ithome.web.DepositController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Deposit;
import com.ithome.web.Bean.ProductName;
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

@WebServlet("/AddNewDepositToData")
public class AddNewDepositToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    private List<Deposit> depositList = new ArrayList<>();
    private DepositDaoController depositDaoController = new DepositDaoController();

    private int DId = 0;
    private String ProductName = null;
    private String ProductNameEn = null;
    private String ProductNameRu = null;
    private int ProductCode = 0;
    private String bankName= null;
    private int DMinAmount= 0;
    private int DMaxAmount= 0;
    private String currancy = null;
    private double DPercentage= 0;
    private int minAge= 0;
    private int maxAge= 0;
    private String DEquippingPossibilities= null;
    private int DEquippingPossibilitiesid= 0;
    private String DEarlierWithdrawalAmount= null;
    private int DEarlierWithdrawalAmountid= 0;
    private String DAutoExtendPeriod= null;
    private int DAutoExtendPeriodid= 0;
    private String DCapitalizationPercent= null;
    private int DCapitalizationPercentid= 0;
    private String bankWebSite = null;
    private String BankLink = null;
    private int Bankid = 0;
    private int timeline =0;
    private int bankCode =0;

    private ProductNameDaoController productNameDaoController = new ProductNameDaoController();
    private List<ProductName> productNameList = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewDepositToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewDepositToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewDepositToData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankInfoFromBankname(bankName);
        createProductCode();
        getCodes();
        checkIfProductNameAdded(createProductNameInData(),request,response);
        checkIfDepositAdded(createNewDepositInData(), request, response);
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


    private void getCodes() {
        DEquippingPossibilitiesid = getDEquippingPossibilitiesid();
        DEarlierWithdrawalAmountid = getDEarlierWithdrawalAmountId();
        DAutoExtendPeriodid = getDAutoExtendPeriodId();
        DCapitalizationPercentid = getDCapitalizationPercentId();
    }

    private int getDEquippingPossibilitiesid() {
        return DEquippingPossibilities.equals("Ոչ") ? 1 : 2;
    }

    private int getDEarlierWithdrawalAmountId() {
        return  DEarlierWithdrawalAmount.equals("Ոչ") ? 1 : 2;
    }

    private int getDAutoExtendPeriodId() {
        return  DAutoExtendPeriod.equals("Ոչ") ? 1 : 2;
    }

    private int getDCapitalizationPercentId() {
       return  DCapitalizationPercent.equals("Ոչ") ? 1 : 2;
    }


    private void checkIfDepositAdded(int newDepositInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (newDepositInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {

            setRequestToPage(request);
            gotoNextPage(request,response);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddNewDepositeComments.jsp").forward(request, response);
    }

    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewDeposit.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", ProductCode);
    }

    private int createNewDepositInData() throws SQLException {
        return depositDaoController.AddNewDeposit(createDataToDatabase());
    }

    private Deposit createDataToDatabase() {
        return new Deposit(ProductCode,Bankid,bankName,bankWebSite,minAge,maxAge,currancy,timeline,ProductName,DMinAmount,DMaxAmount,DPercentage,DEquippingPossibilitiesid,DEquippingPossibilities,DEarlierWithdrawalAmountid
                ,DEarlierWithdrawalAmount,DAutoExtendPeriodid,DAutoExtendPeriod,DCapitalizationPercentid,DCapitalizationPercent,BankLink);
    }

    private void getBankInfoFromBankname(String BankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(BankName);
        for (int i = 0; i <banksList.size() ; i++) {
            bankWebSite= banksList.get(i).getBankWebSite();
            Bankid = banksList.get(i).getBankId();
        }
    }


    private void getParameters(HttpServletRequest request) {
        ProductName = request.getParameter("ProductName");
      /*  ProductCode = Integer.parseInt(request.getParameter("ProductCode"));*/
        ProductNameEn = request.getParameter("ProductNameEng");
        ProductNameRu = request.getParameter("ProductNameRus");
        bankName = request.getParameter("BankName");
        DMinAmount = Integer.parseInt(request.getParameter("DMinAmount"));
        DMaxAmount = Integer.parseInt(request.getParameter("DMaxAmount"));
        currancy = request.getParameter("currancy");
        DPercentage = Double.parseDouble(request.getParameter("DPercentage"));
        minAge = Integer.parseInt(request.getParameter("minAge"));
        maxAge = Integer.parseInt(request.getParameter("maxAge"));
        DEquippingPossibilities = request.getParameter("DEquippingPossibilities");
        DEarlierWithdrawalAmount = request.getParameter("DEarlierWithdrawalAmount");
        DAutoExtendPeriod = request.getParameter("DAutoExtendPeriod");
        timeline = Integer.parseInt(request.getParameter("timeline"));
        DCapitalizationPercent = request.getParameter("DCapitalizationPercent");
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

