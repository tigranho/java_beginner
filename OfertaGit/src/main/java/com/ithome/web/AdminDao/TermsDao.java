package com.ithome.web.AdminDao;

import com.ithome.web.Bean.AboutUs;
import com.ithome.web.Bean.Terms;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TermsDao {

    /**
     * Get all the terms
     *
     * @return
     */
    public List<Terms> getAllTerms() throws SQLException {
        Terms terms = null;
        List<Terms> termsList = new ArrayList<>();
        ResultSet set = null;
        Connection connection = null;
        Statement statment = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `terms` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            termsList(termsList, set);
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
        return termsList;
    }

    /**
     * List of Terms
     *
     * @param termsList
     * @param set
     * @throws SQLException
     */
    private void termsList(List<Terms> termsList, ResultSet set) throws SQLException {
        Terms terms;
        while (set.next()) {
            terms = new Terms();
            terms.setTermsId(set.getInt("termsId"));
            terms.setTermsAm(set.getString("terms_am"));
            terms.setTermsEn(set.getString("terms_ru"));
            terms.setTermsRu(set.getString("terms_en"));


            termsList.add(terms);
        }
    }

    /**
     * Add new terms
     *
     * @param terms
     * @return
     */
    public int AddTerms(Terms terms) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String insertQuery = "INSERT INTO `terms`(`termsId`, `terms_am`, `terms_ru`, `terms_en`)" +
                    " Values(Default,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            statment.setString(1, terms.getTermsAm());
            statment.setString(2, terms.getTermsRu());
            statment.setString(3, terms.getTermsEn());


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
     * get Terms By Id
     *
     * @param termsId
     * @return
     * @throws SQLException
     */
    public List<Terms> getMessagesById(int termsId) throws SQLException {
        Terms terms = null;
        List<Terms> termsList = new ArrayList<>();
        Connection connection = DBConnection.getConnectionToDatabase();
        String sql = "SELECT * FROM `terms` Where termsId=" + termsId;
        Statement statment = connection.createStatement();
        ResultSet set = statment.executeQuery(sql);
        while (set.next()) {
            terms = new Terms();
            termsList(termsList, set);
        }
        return termsList;
    }

    /**
     * Update terms in English
     *
     * @param termsInEng
     * @param termsID
     * @return
     */
    public int UpdateTermsinEng(String termsInEng, int termsID) {
        int rowsUpdated = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "UPDATE `terms`  SET terms_en=? WHERE termsId=" + termsID;
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, termsInEng);
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rowsUpdated;
    }

    /**
     * Update terms in Armenian
     *
     * @param termsInArm
     * @param termsID
     * @return
     */
    public int UpdateTermsinArm(String termsInArm, int termsID) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = DBConnection.getConnectionToDatabase();

            String sql = "UPDATE `terms`  SET terms_am=? WHERE termsId=" + termsID;
            statment = connection.prepareStatement(sql);
            statment.setString(1, termsInArm);
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
     * Update terms in Russian
     *
     * @param termsInRu
     * @param termsID
     * @return
     */
    public int UpdateTermsinRu(String termsInRu, int termsID) {
        int rowsUpdated = 0;

        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "UPDATE `terms`  SET terms_ru=? WHERE termsId=" + termsID;
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, termsInRu);
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rowsUpdated;
    }

    /**
     * Get terms in Russian
     *
     * @return
     */
    public List<Terms> GetTermsInRussian() throws SQLException {
        Terms terms = null;
        List<Terms> termsList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `terms` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            TermsRussian(termsList, set);
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
        return termsList;
    }

    /**
     * Get terms in Russian
     *
     * @return
     */
    public List<Terms> GetTermsInEnglish() throws SQLException {
        Terms terms = null;
        List<Terms> termsList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
             connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `terms` ";
             statment = connection.createStatement();
             set = statment.executeQuery(sql);
            TermsEnglish(termsList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
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
        return termsList;
    }

    /**
     * Get terms in Armenian
     *
     * @return
     */
    public List<Terms> GetTermsInArmenian() throws SQLException {
        Terms terms = null;
        List<Terms> termsList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
             connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `terms` ";
             statment = connection.createStatement();
             set = statment.executeQuery(sql);
            TermsArmenian(termsList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
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
        return termsList;
    }

    /**
     * get Russian terms
     *
     * @param termsList
     * @param set
     * @throws SQLException
     */
    private void TermsArmenian(List<Terms> termsList, ResultSet set) throws SQLException {
        Terms terms;
        while (set.next()) {
            terms = new Terms();
            terms.setTermsId(set.getInt("termsId"));
            terms.setTermsAm(set.getString("terms_am"));
            termsList.add(terms);
        }
    }

    /**
     * get Russian terms
     *
     * @param termsList
     * @param set
     * @throws SQLException
     */
    private void TermsEnglish(List<Terms> termsList, ResultSet set) throws SQLException {
        Terms terms;
        while (set.next()) {
            terms = new Terms();
            terms.setTermsId(set.getInt("termsId"));
            terms.setTermsEn(set.getString("terms_en"));
            termsList.add(terms);
        }
    }


    /**
     * get Russian terms
     *
     * @param termsList
     * @param set
     * @throws SQLException
     */
    private void TermsRussian(List<Terms> termsList, ResultSet set) throws SQLException {
        Terms terms;
        while (set.next()) {
            terms = new Terms();
            terms.setTermsId(set.getInt("termsId"));
            terms.setTermsRu(set.getString("terms_ru"));
            termsList.add(terms);
        }
    }
}
