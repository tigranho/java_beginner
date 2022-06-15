package com.ithome.web.BlogController;

import com.ithome.web.AdminDao.UsefullArticlesDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.UsefulArticles;
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

@WebServlet("/DeleteBlogArmenian")
public class DeleteBlogArmenian extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int UAId = 0;
    private int deleteBlogRussian = 0;
    private List<UsefulArticles> usefulArticlesList = new ArrayList<>();
    private UsefullArticlesDaoController usefullArticlesDaoController = new UsefullArticlesDaoController();
    private int deleteBlog=0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteBlogRussian(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteBlogRussian(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteBlogRussian(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        DeleteArmenianBlogById(UAId,request,response);
    }

    private void DeleteArmenianBlogById(int uaId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        deleteBlog = usefullArticlesDaoController.DeleteArticle(uaId);
        if(deleteBlog > 0) {
            getAllArmenianBlogDetail();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }else {
            getAllArmenianBlogDetail();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }
    }

    private void gotoSamePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowAllArmenianBlog.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("usefulArticlesList", usefulArticlesList);
    }

    private void getAllArmenianBlogDetail() throws SQLException {
        usefulArticlesList = usefullArticlesDaoController.getAllUsefullArticles();
    }

    private void getParameters(HttpServletRequest request) {
        UAId = Integer.parseInt(request.getParameter("UAId"));
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

