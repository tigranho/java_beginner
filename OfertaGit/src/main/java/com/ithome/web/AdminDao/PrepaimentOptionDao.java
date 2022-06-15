package com.ithome.web.AdminDao;

import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Bean.RePaymentOptions;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepaimentOptionDao {


    /**
     * Get Payment option by name
     *
     * @param rePaymentOptions
     * @return
     */
    public List<RePaymentOptions> getRePaymentOptionsByName(String rePaymentOptions) {
        RePaymentOptions rePaymentOptions1 = null;
        List<RePaymentOptions> rePaymentOptionsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `prepaimentcontroller` where `rePaymentOptions` like '%" + rePaymentOptions + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfRePrepaymentOption(rePaymentOptionsList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return rePaymentOptionsList;

    }

    /**
     * Get Payment option by name
     *
     * @param rePaymentOptions
     * @return
     */
    public List<RePaymentOptions> getRePaymentOptionsByArmenianName(String rePaymentOptions) throws SQLException {
        RePaymentOptions rePaymentOptions1 = null;
        List<RePaymentOptions> rePaymentOptionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `prepaimentcontroller` where `rePrepaimentOptionAm` like '%" + rePaymentOptions + "%'";

            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfRePrepaymentOption(rePaymentOptionsList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
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
        return rePaymentOptionsList;

    }

    /**
     * Re payment option list
     *
     * @param rePaymentOptionsList
     * @param set
     * @throws SQLException
     */
    private void ListOfRePrepaymentOption(List<RePaymentOptions> rePaymentOptionsList, ResultSet set) throws SQLException {
        RePaymentOptions rePaymentOptions;
        while (set.next()) {
            rePaymentOptions = new RePaymentOptions();
            rePaymentOptions.setRePrepaimentOptionId(set.getInt("rePrepaimentOptionId"));
            rePaymentOptions.setRePrepaimentOptionAm(set.getString("rePrepaimentOptionAm"));
            rePaymentOptions.setRePrepaimentOptionen(set.getString("rePrepaimentOptionen"));
            rePaymentOptions.setRePrepaimentOptionru(set.getString("rePrepaimentOptionru"));
            rePaymentOptionsList.add(rePaymentOptions);
        }
    }

    /**
     * all repayment options
     *
     * @return
     */
    public List<RePaymentOptions> GetAllRePaymentOption() throws SQLException {
        RePaymentOptions rePaymentOptions = null;
        List<RePaymentOptions> rePaymentOptionsList = new ArrayList<RePaymentOptions>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `prepaimentcontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfRePrepaymentOption(rePaymentOptionsList, set);
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
        return rePaymentOptionsList;
    }

    /**
     * all repayment options
     *
     * @return
     */
    public List<RePaymentOptions> GetAllRePaymentOptionArmenian() throws SQLException {
        RePaymentOptions rePaymentOptions = null;
        List<RePaymentOptions> rePaymentOptionsList = new ArrayList<RePaymentOptions>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `prepaimentcontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ArmenianListOfRePrepaymentOption(rePaymentOptionsList, set);
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
        return rePaymentOptionsList;
    }

    /**
     * Re payment option list
     *
     * @param rePaymentOptionsList
     * @param set
     * @throws SQLException
     */
    private void ArmenianListOfRePrepaymentOption(List<RePaymentOptions> rePaymentOptionsList, ResultSet set) throws SQLException {
        RePaymentOptions rePaymentOptions;
        while (set.next()) {
            rePaymentOptions = new RePaymentOptions();
            rePaymentOptions.setRePrepaimentOptionId(set.getInt("rePrepaimentOptionId"));
            rePaymentOptions.setRePrepaimentOptionAm(set.getString("rePrepaimentOptionAm"));
            rePaymentOptionsList.add(rePaymentOptions);
        }
    }


    /**
     * Connrction to database
     *
     * @return
     */
    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }
}
