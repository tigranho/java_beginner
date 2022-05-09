package com.ithome.web.MicroLoanController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.AdminDao.MicroLoanDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Currancy;
import com.ithome.web.Bean.MicroLoans;
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

@WebServlet("/UpdateMicroDetailsToData")
public class UpdateMicroDetailsToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private int MLId = 0;
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
    private List<MicroLoans> microLoansList = new ArrayList<>();

    private int ProductCode =0;
    private String BankName = null;
    private String ProductName = null;
    private int MLMinAmount = 0;
    private int MLMaxAmount =0;
    private double MLMinLoan =0;
    private double MLMaxLoan =0;
    private double MLFatual=0;
    private String currancy=null;
    private int MMinPeriodDays =0;
    private int MMaxPeriodDays =0;
    private int MMinServiceFee =0;
    private int MMaxServiceFee =0;
    private int minAge=0;
    private int maxAge=0;
    private int Bankid =0;
    private String bankWebSite=null;
    private String BankLink=null;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMicroDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMicroDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void updateMicroDetailsToData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        getBankDetailByBankName(BankName);
        getBankFullDetail();
        getCurrancyFullDetail();
        updateMicroInData(createNewMicroInData(),request,response,MLId);
    }

    private void updateMicroInData(int newMicroInData, HttpServletRequest request, HttpServletResponse response, int mlId) throws ServletException, IOException, SQLException {
        if (newMicroInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getMicroDetailById(mlId);
            setRequestToMicroUpdatePage(request);
            goBackToErrorPage(request, response, message);
        } else {
            String message = "Հաջողությամբ ավելացրեց միկրո վարկը";
            getMicroDetailById(mlId);
            setRequestToMicroUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateMicro.jsp").forward(request, response);
    }

    private void setRequestToMicroUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("MicroFullList", microLoansList);
    }

    private void getMicroDetailById(int mlId) throws SQLException {
        microLoansList = microLoanDaoController.getMicroLoanssById(mlId);
    }

    private int createNewMicroInData() throws SQLException {
        return microLoanDaoController.UpdateMicroInData(prepaireMicroInfoForData(),MLId);
    }

    private MicroLoans prepaireMicroInfoForData() {
        return new MicroLoans(ProductCode,Bankid,BankName,bankWebSite,ProductName,MLMinAmount,MLMaxAmount,MLMinLoan,MLMaxLoan,
                MLFatual,currancy,MMinPeriodDays,MMaxPeriodDays,MMinServiceFee,MMaxServiceFee,minAge,maxAge,BankLink);
    }

    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
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

    private void getParameters(HttpServletRequest request) {
        MLId = Integer.parseInt(request.getParameter("MLId"));
        ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        MLMinAmount = Integer.parseInt(request.getParameter("MLMinAmount"));
        MLMaxAmount = Integer.parseInt(request.getParameter("MLMinaxAmount"));
        MLMinLoan = Double.parseDouble(request.getParameter("MLMinLoan"));
        MLMaxLoan = Double.parseDouble(request.getParameter("MLMaxLoan"));
        MLFatual = Double.parseDouble(request.getParameter("MLFatual"));
        currancy = request.getParameter("currancy");
        MMinPeriodDays = Integer.parseInt(request.getParameter("MMinPeriodDays"));
        MMaxPeriodDays = Integer.parseInt(request.getParameter("MMaxPeriodDays"));
        MMinServiceFee = Integer.parseInt(request.getParameter("MMinServiceFee"));
        MMaxServiceFee = Integer.parseInt(request.getParameter("MMaxServiceFee"));
        minAge = Integer.parseInt(request.getParameter("minAge"));
        maxAge = Integer.parseInt(request.getParameter("maxAge"));
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



