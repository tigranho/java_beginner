package com.ithome.web.SearchController;

import com.ithome.web.AdminDao.*;
import com.ithome.web.Bean.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private List<Product> products;
    private List<Banks> BankList = new ArrayList<>();
    private List<Deposit> DepositList = new ArrayList<>();
    private List<Mortgage> MortgageList = new ArrayList<>();
    private List<ConsumerCredit> ConsumerList = new ArrayList<>();
    private List<MicroLoans> MicroList = new ArrayList<>();
    private List<CarLoans> CarLoanList = new ArrayList<>();
    private List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
    private List<Cards> cardsList = new ArrayList<>();

    private BanksDaoController banksDaoController = new BanksDaoController();
    private DepositDaoController depositDaoController = new DepositDaoController();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();
    private ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
    private CarLoanDao carLoanDao = new CarLoanDao();
    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
    private CardsDao cardsDao = new CardsDao();


    public ProductModel() throws SQLException {
        products = new ArrayList<>();

        products.addAll(getAgLists());
        products.addAll(getBanksLists());
        products.addAll(getDepositsLists());
        products.addAll(getMortgagesLists());
        products.addAll(getConsumerLists());
        products.addAll(getMicroLists());
        products.addAll(getCarLists());
        products.addAll(getCardsLists());

    }

    private List<Product> getCardsLists() throws SQLException {
        cardsList = new ArrayList<>();
        cardsList = cardsDao.getAllCardsList();
        https:
        if (cardsList.size() > 0) {
            for (int i = 0; i < cardsList.size(); i++) {
                String id = String.valueOf(cardsList.get(i).getProductCode());
                String Name = cardsList.get(i).getCardName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Քարտեր"));
                }
            }
        }
        return products;
    }

    private List<Product> getAgLists() throws SQLException {
        agriculturalCreditList = new ArrayList<>();
        agriculturalCreditList = agriculturalCreditDao.getAllAgriculturalCreditList();
        if (agriculturalCreditList.size() > 0) {
            for (int i = 0; i < agriculturalCreditList.size(); i++) {
                String id = String.valueOf(agriculturalCreditList.get(i).getProductCode());
                String Name = agriculturalCreditList.get(i).getProductName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Գյուղատնտեսական"));
                }
            }
        }
        return products;
    }

    private List<Product> getCarLists() throws SQLException {
        CarLoanList = new ArrayList<>();
        CarLoanList = carLoanDao.getAllCarLoans();
        if (CarLoanList.size() > 0) {
            for (int i = 0; i < CarLoanList.size(); i++) {
                String id = String.valueOf(CarLoanList.get(i).getProductCode());
                String Name = CarLoanList.get(i).getProductName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Ավտովարկ"));
                }
            }
        }
        return products;
    }

    private List<Product> getMicroLists() throws SQLException {
        MicroList = new ArrayList<>();
        MicroList = microLoanDaoController.getAllMicroLoans();
        if (MicroList.size() > 0) {
            for (int i = 0; i < MicroList.size(); i++) {
                String id = String.valueOf(MicroList.get(i).getProductCode());
                String Name = MicroList.get(i).getProductName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Միկրովարկ"));
                }
            }
        }
        return products;
    }

    private List<Product> getConsumerLists() throws SQLException {
        ConsumerList = new ArrayList<>();
        ConsumerList = consumerCreditDaoController.getAllCardsList();
        if (ConsumerList.size() > 0) {
            for (int i = 0; i < ConsumerList.size(); i++) {
                String id = String.valueOf(ConsumerList.get(i).getProductCode());
                String Name = ConsumerList.get(i).getProductName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Սպարողական"));
                }
            }
        }
        return products;
    }

    private List<Product> getMortgagesLists() throws SQLException {
        MortgageList = new ArrayList<>();
        MortgageList = mortgageDaoController.getAllMortage();
        if (MortgageList.size() > 0) {
            for (int i = 0; i < MortgageList.size(); i++) {
                String id = String.valueOf(MortgageList.get(i).getProductCode());
                String Name = MortgageList.get(i).getProductName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Հիփոթեք"));
                }
            }
        }
        return products;
    }

    private List<Product> getDepositsLists() throws SQLException {
        DepositList = new ArrayList<>();
        DepositList = depositDaoController.getAllDepositeList();
        if (DepositList.size() > 0) {
            for (int i = 0; i < DepositList.size(); i++) {
                String id = String.valueOf(DepositList.get(i).getProductCode());
                String Name = DepositList.get(i).getProductName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Ավանդներ"));
                }
            }
        }
        return products;
    }

    private List<Product> getBanksLists() throws SQLException {
        BankList = new ArrayList<>();
        BankList = banksDaoController.getAllBanksList();
        if (BankList.size() > 0) {
            for (int i = 0; i < BankList.size(); i++) {
                String id = String.valueOf(BankList.get(i).getBankId());
                String Name = BankList.get(i).getBankName();
                if (id == null || Name == null || Name.equals("") || Name.isEmpty() || Name.equals(" ")) {
                    continue;
                } else {
                    products.add(new Product(id, Name, "", "Բանկեր"));
                }
            }
        }
        return products;
    }

    public List<String> search(String keyword, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        List<String> names = new ArrayList<String>();
        List<String> namesId = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                String strings = "";
                String strings2 = "";
                String strings3 = "";
                String str = "";
                String str2 = "";
                String str3 = "-ում";

                Charset charset = StandardCharsets.UTF_8;
                str = product.getName();
                str2 = product.getPageName();
                byte[] chars = str.getBytes(charset);
                byte[] chars2 = str2.getBytes(charset);
                byte[] chars3 = str3.getBytes(charset);

                strings = new String(chars, StandardCharsets.ISO_8859_1);
                strings2 = new String(chars2, StandardCharsets.ISO_8859_1);
                strings3 = new String(chars3, StandardCharsets.ISO_8859_1);
                System.out.println(strings + " " + strings2 + "" + strings3 + " ID " + product.getId());
                names.add("<span> " + strings + " " + strings2 + "" + strings3 + " ID " + product.getId() + "</span>");
            }
        }
        return names;
}

    /*public List<String> search2(String keyword, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        List<String> names = new ArrayList<String>();
        List<String> namesId = new ArrayList<>();
        for (Product product : products) {

            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                String strings = "";
                String strings2 = "";
                String strings3 = "";
                String str = "";
                String str2 = "";
                String str3 = "-ում";

                Charset charset = StandardCharsets.UTF_8;
                str = product.getName();
                str2 = product.getPageName();
                byte[] chars = str.getBytes(charset);
                byte[] chars2 = str2.getBytes(charset);
                byte[] chars3 = str3.getBytes(charset);

                strings = new String(chars, StandardCharsets.ISO_8859_1);
                strings2 = new String(chars2, StandardCharsets.ISO_8859_1);
                strings3 = new String(chars3, StandardCharsets.ISO_8859_1);
                System.out.println(strings + " " + strings2 + "" + strings3 + " ID " + product.getId());
                names.add(strings + " " + strings2 + "" + strings3 + " ID " + product.getId());


            }
        }
        return names;
    }*/
}