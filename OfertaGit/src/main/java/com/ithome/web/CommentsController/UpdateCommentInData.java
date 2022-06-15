package com.ithome.web.CommentsController;

import com.ithome.web.AdminDao.CommentDaoComtroller;
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

@WebServlet("/UpdateCommentInData")
public class UpdateCommentInData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int ProductCodes = 0;
    private String comment1Am = "";
    private String comment2Am = "";
    private String comment3Am = "";
    private CommentDaoComtroller commentDaoComtroller = new CommentDaoComtroller();
    private List<Comments> commentsList = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateCommentInData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateCommentInData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCommentInData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        updateCommentsInData(request,response, ProductCodes);
    }

    private void updateCommentsInData(HttpServletRequest request, HttpServletResponse response, int productCode) throws ServletException, IOException, SQLException {
        if (createNewAGInData() > 0) {
            String message = "ձեր թարմացումը հաջող է";
            getCommentsDetailByProductCode(productCode);
            setRequestToCommentsUpdatePage(request);
            gotoNextPage(request,response,message);
        } else {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getCommentsDetailByProductCode(productCode);
            setRequestToCommentsUpdatePage(request);
            goBackToErrorPage(request, response, message);

        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateComments.jsp").forward(request, response);
    }

    private void setRequestToCommentsUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("commentsList", commentsList);
    }

    private void getCommentsDetailByProductCode(int productCode) throws SQLException {
        commentsList = commentDaoComtroller.getCommentByProdcutCode(productCode);
    }


    private int createNewAGInData() throws SQLException {
        return commentDaoComtroller.updateAgInData(prepaireCommentToData(), ProductCodes);

    }

    private Comments prepaireCommentToData() {
        return new Comments(comment1Am,comment2Am,comment3Am);
    }

    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("comment1Am")!=null) {
            comment1Am = request.getParameter("comment1Am");
        }
        if(request.getParameter("comment2Am")!=null) {
            comment2Am = request.getParameter("comment2Am");
        }
        if(request.getParameter("comment3Am")!=null) {
            comment3Am = request.getParameter("comment3Am");
        }

        ProductCodes = Integer.parseInt(request.getParameter("productCode"));
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



