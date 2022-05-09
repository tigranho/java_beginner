package com.ithome.web.AdminDao;

import com.ithome.web.Bean.ProductName;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductNameDaoController {
    /**
     * Add new agriculture credit
     *
     * @param productName
     * @return
     * @throws SQLException
     */
    public int AddNewProductName(ProductName productName) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `productnamecontroller`" +
                    "(`productid`, `productCode`, `productNameAm`, `prodeuctNameEn`," +
                    "`productNameRu`) VALUES (Default,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(productName, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }

        }
        return rowsAffected;
    }

    public List<ProductName> getAllProductNames() throws SQLException {
        ProductName productName = null;
        List<ProductName> productNameList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `productnamecontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfProductName(productNameList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return productNameList;
    }

    /**
     * Update ag in data
     *
     * @param productName
     * @param productCode
     * @return
     */
    public int updateAgInData(ProductName productName, int productCode) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String sql = "UPDATE `productnamecontroller`  " +
                    "SET productNameAm=?,prodeuctNameEn=?,productNameRu=?" +
                    "WHERE productCode=" + productCode;
            statment = connection.prepareStatement(sql);
            setStatmentForNonMarketing(productName, statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }

        }
        return rowsUpdated;

    }

    /**
     * get card by card Min Service Fee
     *
     * @param ProductCode
     * @return
     */
    public List<ProductName> getProductNameByProdcutCode(int ProductCode) throws SQLException {
        ProductName productName = null;
        List<ProductName> productNameList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `productnamecontroller` WHERE productCode=" + ProductCode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfProductName(productNameList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return productNameList;
    }

    /**
     * @param ProductCode
     * @return
     */
    public String getARProductNamesByProdcutCode(int ProductCode) throws SQLException {
        String productNames = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
             connection = connectToData();
            String sql = "SELECT *  FROM  `productnamecontroller` WHERE productCode=" + ProductCode;
             statment = connection.prepareStatement(sql);
             set = statment.executeQuery();
            productNames = set.getString("ProductNameAm");

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return productNames;
    }

    /**
     * @param productCode
     * @return
     */
    public String getRuProductNamesByProdcutCode(int productCode) {
        String productNames = null;
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `productnamecontroller` WHERE productCode=" + productCode;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            productNames = set.getString("ProductNameRu");

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return productNames;
    }

    /**
     * @param productCode
     * @return
     */
    public String getEnProductNamesByProdcutCode(int productCode) {
        String productNames = null;
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `productnamecontroller` WHERE productCode=" + productCode;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            productNames = set.getString("ProductNameEn");

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return productNames;
    }

    /**
     * List of the product names
     *
     * @param productNameList
     * @param set
     * @throws SQLException
     */
    private void ListOfProductName(List<ProductName> productNameList, ResultSet set) throws SQLException {
        ProductName pr;
        while (set.next()) {
            pr = new ProductName();
            pr.setProductid(set.getInt("productid"));
            pr.setProductCode(set.getInt("ProductCode"));
            pr.setProductNameAm(set.getString("ProductNameAm"));
            pr.setProdeuctNameEn(set.getString("ProdeuctNameEn"));
            pr.setProductNameRu(set.getString("ProductNameRu"));

            productNameList.add(pr);
        }
    }

    private void setStatmentForNonMarketing(ProductName productName, PreparedStatement statement) throws SQLException {
        statement.setString(1, productName.getProductNameAm());
        statement.setString(2, productName.getProdeuctNameEn());
        statement.setString(3, productName.getProductNameRu());
    }

    private void setStatment(ProductName productName, PreparedStatement statement) throws SQLException {
        statement.setInt(1, productName.getProductCode());
        statement.setString(2, productName.getProductNameAm());
        statement.setString(3, productName.getProdeuctNameEn());
        statement.setString(4, productName.getProductNameRu());
    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }


}
