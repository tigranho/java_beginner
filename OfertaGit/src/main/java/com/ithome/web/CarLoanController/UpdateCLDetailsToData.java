package com.ithome.web.CarLoanController;
import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.AdminDao.CurrancyDao;
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


@WebServlet("/UpdateCLDetailsToData")
public class UpdateCLDetailsToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private CarLoanDao carLoanDao = new CarLoanDao();
    private List<CarLoans> carLoansList = new ArrayList<>();

    private int ProductCodeInCarLoan = 0;
    private String ProductName = null;
    private int CLMinAmount = 0;
    private int CLMaxAmount = 0;
    private double CLMinLoan = 0;
    private double CLMacLoan = 0;
    private double CLPerFactual = 0;
    private int CLMinDownPayment = 0;
    private int CLMaxDownPayment = 0;
    private int CLMinPeriodMonths = 0;
    private int CLMaxPeriodMonth = 0;
    private int CLMinServiceFee = 0;
    private int ClMaxServiceFee = 0;
    private int ClMinAge = 0;
    private int ClMaxAge = 0;
    private String BankName = null;
    private String currancy = null;
    private int Bankid = 0;
    private String bankWebSite = null;
    private String BankLink = null;
    private int CLId =0;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateClDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateClDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateClDetailsToData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        getBankDetailByBankName(BankName);
        getBankFullDetail();
        getCurrancyFullDetail();
        updateCarLoantInData(createNewCLInData(),request,response,CLId);
    }

    private void updateCarLoantInData(int newCLInData, HttpServletRequest request, HttpServletResponse response, int clId) throws ServletException, IOException, SQLException {
        if (newCLInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getCLDetailById(clId);
            setRequestToCLUpdatePage(request);
            goBackToErrorPage(request, response, message);
        } else {
            String message = "Հաջողությամբ թարմացրել եէ մեքենայի վարկը";
            getCLDetailById(clId);
            setRequestToCLUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateCL.jsp").forward(request, response);
    }

    private void setRequestToCLUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("carLoansList", carLoansList);
    }

    private void getCLDetailById(int clId) throws SQLException {
        carLoansList = carLoanDao.getCarLoansById(clId);
    }

    private int createNewCLInData() throws SQLException {
        return carLoanDao.updateClInData(prepairCarLoanInfoInData(),CLId);
    }

    private CarLoans prepairCarLoanInfoInData() {
        return new CarLoans(ProductCodeInCarLoan,Bankid,BankName,bankWebSite,ClMinAge,ClMaxAge,currancy,ProductName,CLMinAmount,CLMaxAmount,CLMinLoan,CLMacLoan,CLPerFactual,CLMinDownPayment,CLMaxDownPayment,CLMinPeriodMonths,CLMaxPeriodMonth,CLMinServiceFee,ClMaxServiceFee,BankLink);
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }


    private void getBankDetailByBankName(String BankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(BankName);
        for (int i = 0; i <banksList.size() ; i++) {
            bankWebSite= banksList.get(i).getBankWebSite();
            Bankid = banksList.get(i).getBankId();
        }
    }

    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void getParameters(HttpServletRequest request) {
        CLId = Integer.parseInt(request.getParameter("CLId"));
        ProductCodeInCarLoan = Integer.parseInt(request.getParameter("ProductCode"));
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        CLMinAmount = Integer.parseInt(request.getParameter("CLMinAmount"));
        CLMaxAmount = Integer.parseInt(request.getParameter("CLMaxAmount"));
        CLMinLoan = Double.parseDouble(request.getParameter("CLMinLoan"));
        CLMacLoan = Double.parseDouble(request.getParameter("CLMacLoan"));
        CLPerFactual = Double.parseDouble(request.getParameter("CLPerFactual"));
        CLMinDownPayment = Integer.parseInt(request.getParameter("CLMinDownPayment"));
        CLMaxDownPayment = Integer.parseInt(request.getParameter("CLMaxDownPayment"));
        currancy = request.getParameter("currancy");
        CLMinPeriodMonths = Integer.parseInt(request.getParameter("CLMinPeriodMonths"));
        CLMaxPeriodMonth = Integer.parseInt(request.getParameter("CLMaxPeriodMonth"));
        CLMinServiceFee = Integer.parseInt(request.getParameter("CLMinServiceFee"));
        ClMaxServiceFee = Integer.parseInt(request.getParameter("ClMaxServiceFee"));
        ClMinAge = Integer.parseInt(request.getParameter("ClMinAge"));
        ClMaxAge = Integer.parseInt(request.getParameter("ClMaxAge"));
        BankLink = request.getParameter("BankLink");
    }

    /**
     * Controlling the session for admin using helper classes
     * @param request
     * @param response
     * @throws IOException
     */
    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        getSession(session, request, response );
    }

    /**
     * Fill admin in list with the specific id
     * @param adminid
     */
    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
    }

    /**
     * get admin admin id by username from session
     * @param request
     * @param response
     */
    private void getAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        adminId = adminChecker.getAdminId(username);
        getFullAdminList(adminId);
    }

    /**
     * Session of uadmin if no session true send to login page else get the seesion key
     * @param session
     * @param request
     * @param response
     * @throws IOException
     */
    private void getSession(HttpSession session , HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(checker.checkSession(request, response)){
            username = checker.requestSessionofAdmin(session);
        }else{
            response.sendRedirect("/admin/SignIn.jsp");
        }
    }
}



