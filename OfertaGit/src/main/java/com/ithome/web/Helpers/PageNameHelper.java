package com.ithome.web.Helpers;

import javax.servlet.http.HttpServletRequest;

public class PageNameHelper {

    public String pageName(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1);

    }

    public String ArmPageName(String pageName) {
        switch (pageName) {
            case "App":
                return "Գլխաոր";
            case "ContactUs":
                return "Կապ մեզ հետ";
            case "About":
                return "Մեր մասին";
            case "Ofbanks":
                return "Գործնկերներ";
            case "Credits":
                return "ԱՎԱՆԴՆԵՐ";
            case "Blog":
                return "Բլոկ";
            case "DepositClient":
                return "ԱՎԱՆԴՆԵՐ";
            case "Calculate":
                return "ԱՎԱՆԴՆԵՐ";
            case "MortgageClient":
                return "Հիփոթեք";
            case "Mortgage":
                return "Հիփոթեք";
            case "CreditSend":
                return "";
            case "MortgagClient":
                return "Հիփոթեք";
            case "CalculateMort":
                return "Հիփոթեք";
            case "MortgageClientss":
                return "Հիփոթեք";
            case "ՀԻՓՈԹԵՔ":
                return "Հիփոթեք";
            case "Հիփոթեք":
                return "Հիփոթեք";
            case "ՍՊԱՌՈՂԱԿԱՆ":
                return "Սպարողական";
            case "ConsumerCalculate":
                return "Սպարողական";
            case "ConsumerClient":
                return "Սպարողական";
            case "MortgageClientsss":
                return "Հիփոթեք";
            case "AutoClient":
                return "ԱՎՏՈՎԱՐԿ";
            case "Ավտովարկ":
                return "ԱՎՏՈՎԱՐԿ";
            case "AutoCalulate":
                return "ԱՎՏՈՎԱՐԿ";
            case "ԱՎՏՈՎԱՐԿ":
                return "Ավտովարկ";
            case "Միկրովարկ":
                return "ՄԻԿՐՈՎԱՐԿ";
            case "ՄԻԿՐՈՎԱՐԿ":
                return "Միկրովարկ";
            case "AgCalculate":
                return "ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ";
            case "AcClient":
                return "ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ";
            case "Գյուղատնտեսական":
                return "ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ";
            case "ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ":
                return "Գյուղատնտեսական";
            default:
                return "Գլխաոր";



        }
    }
}
