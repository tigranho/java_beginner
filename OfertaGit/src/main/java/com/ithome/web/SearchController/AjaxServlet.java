package com.ithome.web.SearchController;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        String term = request.getParameter("search_text");
        System.out.println("term " + term);

        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        ProductModel productModel = null;
        try {
            productModel = new ProductModel();
        } catch (SQLException e) {
            e.printStackTrace();
            out.print(gson.toJson(e));
        }
        if (productModel != null) {
            out.print(gson.toJson(productModel.search(term, request, response)));
        }

        out.flush();
        out.close();

      /*  Gson gson = new Gson();
        String term = request.getParameter("term");
        ProductModel productModel = null;
        productModel = new ProductModel();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(productModel.search(term)));
        out.flush();
        out.close();*/
    }

}
