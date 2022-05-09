package com.ithome.web.CommentsController;

import com.ithome.web.AdminDao.CommentDaoComtroller;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Comments;
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

@WebServlet("/UpdateComments")
public class UpdateComments extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int productCode = 0;
    private List<Comments> commentsList = new ArrayList<>();
    private CommentDaoComtroller commentDaoComtroller = new CommentDaoComtroller();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateAgComments(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateAgComments(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAgComments(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        getAllCommentsByProductCode(productCode);
        setRequestToPage(request);
        gotoUpdateAgPage(request,response);
    }

    private void gotoUpdateAgPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/UpdateComments.jsp").forward(request, response);
    }

    private void getAllCommentsByProductCode(int productCode) throws SQLException {
        commentsList =commentDaoComtroller.getCommentByProdcutCode(productCode);
        if(commentsList.size() == 0){
            commentsList.add(0,new Comments("Nothing","Nothing","Nothing","","","","","",""));
        }
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("commentsList", commentsList);
        request.setAttribute("ProductCode", productCode);
    }

    private void getParameters(HttpServletRequest request) {
        productCode = Integer.parseInt(request.getParameter("productCode"));
    }



    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        getSession(session, request, response );
    }

    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
    }

    private void getAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        adminId = adminChecker.getAdminId(username);
        getFullAdminList(adminId);
    }

    private void getSession(HttpSession session , HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(checker.checkSession(request, response)){
            username = checker.requestSessionofAdmin(session);
        }else{
            response.sendRedirect("/admin/SignIn.jsp");
        }
    }
}


