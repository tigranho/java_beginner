package com.ithome.web.CardController;

import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddNewPDF")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNewPDF extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private CardsDao cardsDao = new CardsDao();
    private int productCode = 0;


    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY_CARD = "uploadCard";
    private int cardId =0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewPDF(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewPDF(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewPDF(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        UploadImageToServer(request, response);
    }


    private void UploadImageToServer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        System.out.println("appPath +" + appPath);
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY_CARD;
        System.out.println("savePath +" + savePath);

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            System.out.println("fileName +" + fileName);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            String filepat = UPLOAD_DIRECTORY_CARD + "/" + fileName;
            System.out.println("filepat +" + filepat);
            part.write(savePath + File.separator + fileName);

            AddCartImageToData(request, response, filepat, productCode);
        }

    }


    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    /**
     * add or update card in data
     *
     * @param request
     * @param response
     * @param filepat
     * @param productCode
     */
    private void AddCartImageToData(HttpServletRequest request, HttpServletResponse response, String filepat, int productCode) throws ServletException, IOException, SQLException {
        String linkToSave =  "http://www.oferta.am/" +filepat;
        Cards cards = new Cards(linkToSave);
        int UpdateImageInData = cardsDao.UpdateCardPDFInData(cards, productCode);
        if (UpdateImageInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, կրկին փորձեք";
            setRequestToPage(request);
            goBackTofinalPage(request, response, message);

        } else {
            String message = "Հաջողությամբ ավելացրեցիք նոր քարդի տվյալները";
            setRequestToPage(request);
            goBackTofinalPage(request, response, message);

        }
    }

    private void goBackTofinalPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.setAttribute("productcode", productCode);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);

    }


    private void getParameters(HttpServletRequest request) {
        productCode = Integer.parseInt(request.getParameter("productcode"));
        //cardId = Integer.parseInt(request.getParameter("cardId"));
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

