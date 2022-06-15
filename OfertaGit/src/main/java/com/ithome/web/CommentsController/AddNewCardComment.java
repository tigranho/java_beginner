package com.ithome.web.CommentsController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CommentDaoComtroller;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
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

@WebServlet("/AddNewCardComment")
public class AddNewCardComment extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    private List<Banks> banksList = new ArrayList<>();
    private List<Comments> commentsList = new ArrayList<>();
    private Comments comments = new Comments();
    private CommentDaoComtroller commentDaoComtroller = new CommentDaoComtroller();

    private String comment1Am = null;
    private String comment2Am = null;
    private String comment3Am = null;
    private String comment1En = null;
    private String comment2En = null;
    private String comment3En = null;
    private String comment1Ru = null;
    private String comment2Ru = null;
    private String comment3Ru = null;
    private int productcode = 0;
    private int cardId = 0;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewComment(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewComment(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewComment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);

        getAdminInfo(request, response);
        getParameters(request);
        checkIfCommentsAdded(creatNewCommentInData(), request, response);
    }

    private void checkIfCommentsAdded(int creatNewCommentInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (creatNewCommentInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {
            setRequestToPage(request);
            gotoNextPage(request, response);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/AddNewCardImage.jsp").forward(request, response);
    }


    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewComments.jsp").forward(request, response);
    }


    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", productcode);
        request.setAttribute("cardId", cardId);
    }

    private int creatNewCommentInData() throws SQLException {
        return commentDaoComtroller.AddNewComment(createCommentDaoToData());
    }

    private Comments createCommentDaoToData() {
        return new Comments(productcode, comment1Am, comment2Am, comment3Am, comment1En, comment2En, comment3En, comment1Ru, comment2Ru, comment3Ru);
    }

    private void getParameters(HttpServletRequest request) {
        productcode = Integer.parseInt(request.getParameter("productcode"));
        cardId = Integer.parseInt(request.getParameter("cardId"));
        comment1Am = request.getParameter("comment1Am");
        comment1Am = request.getParameter("comment1Am");
        comment1Am = request.getParameter("comment1Am");
        comment2Am = request.getParameter("comment2Am");
        comment3Am = request.getParameter("comment3Am");
        comment1En = request.getParameter("comment1En");
        comment2En = request.getParameter("comment2En");
        comment3En = request.getParameter("comment3En");
        comment1Ru = request.getParameter("comment1Ru");
        comment2Ru = request.getParameter("comment2Ru");
        comment3Ru = request.getParameter("comment3Ru");
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

