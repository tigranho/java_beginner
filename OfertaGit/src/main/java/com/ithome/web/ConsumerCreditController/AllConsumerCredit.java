package com.ithome.web.ConsumerCreditController;

import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.AdminDao.ConsumerCreditDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.CarLoans;
import com.ithome.web.Bean.ConsumerCredit;
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

@WebServlet("/AllConsumerCredit")
public class AllConsumerCredit extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
    private List<ConsumerCredit> consumerCreditList = new ArrayList<>();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            allConsumerCredit(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            allConsumerCredit(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void allConsumerCredit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        GetAllConsumerCreditDetail();
        setRequestToConsumerCreditPage(request);
        gotoToConsumerCreditPage(request,response);
    }

    private void gotoToConsumerCreditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowAllConsumerCredit.jsp").forward(request, response);
    }

    private void setRequestToConsumerCreditPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("consumerCreditList", consumerCreditList);
    }

    private void GetAllConsumerCreditDetail() throws SQLException {
        consumerCreditList = consumerCreditDaoController.getAllCardsList();
    }


    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        getSession(session, request, response);
    }

    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
    }


    private void getAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        adminId = adminChecker.getAdminId(username);
        getFullAdminList(adminId);
    }


    private void getSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (checker.checkSession(request, response)) {
            username = checker.requestSessionofAdmin(session);
        } else {
            response.sendRedirect("/admin/SignIn.jsp");
        }
    }
}



