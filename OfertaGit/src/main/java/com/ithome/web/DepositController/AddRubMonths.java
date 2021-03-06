package com.ithome.web.DepositController;

import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Deposit;
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

@WebServlet("/AddRubMonths")
public class AddRubMonths extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private DepositDaoController depositDaoController = new DepositDaoController();

    private int productcode = 0;
    private double RUB1Month = 0.0;
    private double RUB3Month = 0.0;
    private double RUB6Month = 0.0;
    private double RUB9Month = 0.0;
    private double RUB12Month = 0.0;
    private double RUB18Month = 0.0;
    private double RUB24Month = 0.0;
    private double RUB36Month = 0.0;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addAmdMonths(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addAmdMonths(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAmdMonths(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);

        getAdminInfo(request, response);
        getParameters(request);
        checkIfAmdMonthsAdded(creatNewCommentInData(), request, response);
    }

    private int creatNewCommentInData() throws SQLException {
        return depositDaoController.AddRubMonths(createCommentDaoToData(),productcode);
    }

    private Deposit createCommentDaoToData() {
        return new Deposit(RUB1Month, RUB3Month, RUB6Month, RUB9Month, RUB12Month,RUB18Month, RUB24Month, RUB36Month);
    }

    private void checkIfAmdMonthsAdded(int creatNewCommentInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (creatNewCommentInData == 0) {
            String message = "??????-???? ???????? ???????? ??????????????, ???????????? ??????????";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {
            String message = "???????????????????????? ?????????????????? ??????????????????";
            setRequestToPage(request);
            gotoNextPage(request, response,message);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddDepositMonths3.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", productcode);

    }

    private void getParameters(HttpServletRequest request) {
        productcode = Integer.parseInt(request.getParameter("productcode"));
        RUB1Month = Double.parseDouble(request.getParameter("RUB1Month"));
        RUB3Month = Double.parseDouble(request.getParameter("RUB3Month"));
        RUB6Month = Double.parseDouble(request.getParameter("RUB6Month"));
        RUB9Month = Double.parseDouble(request.getParameter("RUB9Month"));
        RUB12Month = Double.parseDouble(request.getParameter("RUB12Month"));
        RUB18Month = Double.parseDouble(request.getParameter("RUB18Month"));
        RUB24Month = Double.parseDouble(request.getParameter("RUB24Month"));
        RUB36Month = Double.parseDouble(request.getParameter("RUB36Month"));

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
