package com.ithome.web.AdminDao;

import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanksDaoController {

    /**
     * get all admin info by adminId
     *
     * @return
     */
    public List<Banks> getAllBanksList() throws SQLException {
        Banks banks = null;
        List<Banks> banksList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `bankscontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListBanks(banksList, set);
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
        return banksList;
    }

    /**
     * Get banks By Id
     *
     * @param bankId
     * @return
     */
    public List<Banks> getBankInfoById(int bankId) throws SQLException {

        Banks banks = null;
        List<Banks> banksList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();

            String sql = "SELECT *  FROM  `bankscontroller` WHERE bankid=" + bankId;
            //  System.out.println("Connection SQL :  " + sql);
            // set parameters with PreparedStatement
            statment = connection.prepareStatement(sql);
            // execute the statement and check whether user exists
            set = statment.executeQuery();
            ListBanks(banksList, set);

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
        return banksList;
    }

    public List<Banks> getBankInfoByBankCode(int bankCode) throws SQLException {

        Banks banks = null;
        List<Banks> banksList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();

            String sql = "SELECT *  FROM  `bankscontroller` WHERE bankcode=" + bankCode;
            //  System.out.println("Connection SQL :  " + sql);
            // set parameters with PreparedStatement
            statment = connection.prepareStatement(sql);
            // execute the statement and check whether user exists
            set = statment.executeQuery();
            ListBanks(banksList, set);

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
        return banksList;
    }

    /**
     * @param bankId
     * @return
     */
    public String getBankImageById(int bankId) throws SQLException {
        String bankImage = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT `bankSmallLogo`  FROM  `bankscontroller` WHERE bankid=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                bankImage = set.getString("bankSmallLogo");
            }

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
        return bankImage;
    }


    public String getBankBigImageById(int bankId) throws SQLException {
        String bankImage = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT `bankBigLogo`  FROM  `bankscontroller` WHERE bankid=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                bankImage = set.getString("bankBigLogo");
            }

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
        return bankImage;
    }

    /**
     * Set of Banks
     *
     * @param bankList
     * @param set
     * @throws SQLException
     */
    private void ListBanks(List<Banks> bankList, ResultSet set) throws SQLException {
        Banks banks;
        while (set.next()) {
            banks = new Banks();
            banks.setBankId(set.getInt("bankId"));
            banks.setBankCode(set.getInt("bankCode"));
            banks.setBankName(set.getString("bankName"));
            banks.setBankAddress(set.getString("bankAddress"));
            banks.setBankPhoneNumber(set.getString("bankPhoneNumber"));
            banks.setBankfaxNumber(set.getString("bankfax"));
            banks.setBankHQ(set.getString("bankHQ"));
            banks.setBankBranches(set.getInt("bankBranches"));
            banks.setBankATMNumbers(set.getInt("bankAtmNumbers"));
            banks.setBankSwiftCode(set.getString("bankSwiftCode"));
            banks.setBankWebSite(set.getString("bankWebSite"));
            banks.setBankEmailAddress(set.getString("bankEmailAddress"));
            banks.setBankBigLogo(set.getString("bankBigLogo"));
            banks.setBankSmallLogo(set.getString("bankSmallLogo"));

            bankList.add(banks);
        }
    }

    /**
     * Adding new Bank
     *
     * @param banks
     * @return
     */
    public int AddBank(Banks banks) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `bankscontroller`(`bankId`,`bankName`,`bankcode`, `bankAddress`,`bankPhoneNumber` ," +
                    "`bankfax`,`bankHQ`,`bankBranches`,`bankAtmNumbers`,`bankSwiftCode`,`bankWebSite`," +
                    "`bankEmailAddress`,`bankBigLogo`,`bankSmallLogo`) values(Default,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(banks, statment);

            rowsAffected = statment.executeUpdate();

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
        return rowsAffected;
    }


    /**
     * Update banks
     *
     * @param banks
     * @param bankId
     * @return
     */
    public int UpdateBank(Banks banks, int bankId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `bankscontroller`  " +
                    "SET bankName=?," +
                    "bankCode=?, bankAddress=?," +
                    "bankPhoneNumber=?," +
                    "bankfax=?,bankHQ=?,bankBranches=?,bankAtmNumbers=?," +
                    "bankSwiftCode=?,bankWebSite=?,bankEmailAddress=? WHERE bankId=" + bankId;
            statment = connection.prepareStatement(sql);
            setStatmentUpdateText(banks, statment);

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
     * Bank setting statments
     *
     * @param banks
     * @param statement
     * @throws SQLException
     */
    private void setStatmentUpdateText(Banks banks, PreparedStatement statement) throws SQLException {
        statement.setString(1, banks.getBankName());
        statement.setInt(2, banks.getBankCode());
        statement.setString(3, banks.getBankAddress());
        statement.setString(4, banks.getBankPhoneNumber());
        statement.setString(5, banks.getBankfaxNumber());
        statement.setString(6, banks.getBankHQ());
        statement.setInt(7, banks.getBankBranches());
        statement.setInt(8, banks.getBankATMNumbers());
        statement.setString(9, banks.getBankSwiftCode());
        statement.setString(10, banks.getBankWebSite());
        statement.setString(11, banks.getBankEmailAddress());

    }

    /**
     * Bank setting statments
     *
     * @param banks
     * @param statement
     * @throws SQLException
     */
    private void setStatment(Banks banks, PreparedStatement statement) throws SQLException {
        statement.setString(1, banks.getBankName());
        statement.setInt(2, banks.getBankCode());
        statement.setString(3, banks.getBankAddress());
        statement.setString(4, banks.getBankPhoneNumber());
        statement.setString(5, banks.getBankfaxNumber());
        statement.setString(6, banks.getBankHQ());
        statement.setInt(7, banks.getBankBranches());
        statement.setInt(8, banks.getBankATMNumbers());
        statement.setString(9, banks.getBankSwiftCode());
        statement.setString(10, banks.getBankWebSite());
        statement.setString(11, banks.getBankEmailAddress());
        statement.setString(12, banks.getBankBigLogo());
        statement.setString(13, banks.getBankSmallLogo());

    }

    /**
     * Update or add bank image
     *
     * @param banks
     * @param bankId
     * @return
     */
    public int UpdateBankImage(Banks banks, int bankId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String sql = "UPDATE `bankscontroller`  SET bankBigLogo=? WHERE bankId=" + bankId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, banks.getBankBigLogo());
            /* statment.setString(2, banks.getBankSmallLogo());*/
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



    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    /**
     * Upodate or add the small image
     *
     * @param filePath
     * @param bankId
     * @return
     */
    public int UpdateBankSmallImage(String filePath, int bankId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `bankscontroller`  SET bankSmallLogo=? WHERE bankId=" + bankId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, filePath);
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
     * Delet Bank
     *
     * @param bankId
     * @return
     */
    public int DeleteBank(int bankId) throws SQLException {
        Connection connection = null;
        PreparedStatement statment = null;
        int rowsDeleted = 0;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `bankscontroller` WHERE  bankId=" + bankId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Message was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return rowsDeleted;
    }

    /**
     * Load bank with bank name
     *
     * @param bankName
     * @return
     */
    public List<Banks> getBankFullInfoByName(String bankName) throws SQLException {
        Banks banks = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set =null;

        List<Banks> agriculturalCreditList = new ArrayList<>();
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `bankscontroller` where `bankName` like '%" + bankName + "%'";

            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfBanksSet(agriculturalCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }if(set != null){
                set.close();
            }
        }
        return agriculturalCreditList;
    }

    private void ListOfBanksSet(List<Banks> banksList, ResultSet set) throws SQLException {
        Banks banks;
        while (set.next()) {
            banks = new Banks();
            banks.setBankId(set.getInt("bankId"));
            banks.setBankCode(set.getInt("bankCode"));
            banks.setBankName(set.getString("bankName"));
            banks.setBankAddress(set.getString("bankAddress"));
            banks.setBankPhoneNumber(set.getString("bankPhoneNumber"));
            banks.setBankfaxNumber(set.getString("bankfax"));
            banks.setBankHQ(set.getString("bankHQ"));
            banks.setBankBranches(set.getInt("bankBranches"));
            banks.setBankATMNumbers(set.getInt("bankATMNumbers"));
            banks.setBankWebSite(set.getString("bankWebSite"));
            banks.setBankEmailAddress(set.getString("bankEmailAddress"));
            banks.setBankSwiftCode(set.getString("bankSwiftCode"));
            banks.setBankBigLogo(set.getString("bankBigLogo"));
            banks.setBankSmallLogo(set.getString("bankBigLogo"));

            banksList.add(banks);
        }
    }



}
