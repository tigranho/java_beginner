package com.ithome.web.Helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionChecker {
    private String username = null;
    private String sessionId = null;

    public boolean checkSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        //get admin session
        username = (String) session.getAttribute("admins");
        if (username == null) {
            return false;
        } else {
            System.out.println("session helper username : "  + username);
            return true;
        }
    }

    public boolean checkSessionUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        //get admin session
        String sessionId = (String) session.getAttribute("admins");
        if (sessionId == null) {
            return false;
        } else {
            System.out.println("session helper username : "  + sessionId);
            return true;
        }
    }

    public String requestSessionofAdmin(HttpSession session) {
        return String.valueOf(session.getAttribute("admins"));
    }

    public String requestSessionofUser(HttpSession session) {
        return String.valueOf(session.getAttribute("sessionId"));
    }
}
