package com.ithome.web.AdminController;

import com.ithome.web.AdminDao.AdminDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateAdminInfoFinalStep")
public class UpdateAdminInfoFinalStep extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int pinCode =0;
    private String adminUsername = null;
    private String adminPassword = null;
    private Admin admin ;
    private AdminDaoController adminDaoController = new AdminDaoController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateAdminInfoFinalStep(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateAdminInfoFinalStep(request,response);
    }

    private void updateAdminInfoFinalStep(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        sessionControlling(request,response);
        getParameters(request);
        getAdminInfo(request,response);
        updateAdminInfoInData(prepaireAdminInfoForData(),request,response,adminId);

    }

    private void updateAdminInfoInData(Admin prepaireAdminInfoForData, HttpServletRequest request, HttpServletResponse response,int adminId) throws ServletException, IOException {
        int row = adminDaoController.updateAdminInfo(prepaireAdminInfoForData,adminId);
        if(row ==0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ, կրկին փորձեք: ";
            setRequestToPage(request);
            getFullAdminList(adminId);
            gotoPage(request,response, message);

        }else{
            String message = "Դուք հաջողությամբ թարմացնում եք կառավարիչի տվյալները: ";
            setRequestToPage(request);
            getFullAdminList(adminId);
            gotoPage(request,response, message);
        }
    }



    private void gotoPage(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
        request.setAttribute("message", message);
       /* request.setAttribute("adminFullInfo", adminList);*/
        request.getRequestDispatcher("/WEB-INF/updateAdminInfo.jsp").forward(request, response);
    }

    private Admin prepaireAdminInfoForData() {
        return new Admin(adminUsername,adminPassword,pinCode);
    }

    /**
     * prepaire requests to the page
     * @param request
     */
    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
    }

    /**
     * getparameters
     * @param request
     */
    private void getParameters(HttpServletRequest request) {
        pinCode = Integer.parseInt(request.getParameter("adminPinCode"));
        adminUsername = request.getParameter("adminUsername");
        adminPassword = request.getParameter("adminPassword");
        adminId = Integer.parseInt(request.getParameter("adminid"));

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
     * Fill admin in list with the specific id
     * @param adminid
     */
    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
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
