package com.ithome.web.AdminDao;

import com.ithome.web.Bean.UsefulArticles;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsefullArticlesDaoController {

    /**
     * Show all Usefull Articles
     *
     * @return
     */
    public List<UsefulArticles> getAllUsefullArticles() throws SQLException {
        UsefulArticles usefulArticles = null;
        List<UsefulArticles> usefulArticlesList = new ArrayList<>();
        ResultSet set = null;
        Connection connection = null;
        Statement statment = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `userfularticlescontroller` ORDER By `UAId` DESC";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            UsefullArticlesList(usefulArticlesList, set);
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
        return usefulArticlesList;
    }


    public List<UsefulArticles> getAllUsefullArticlesAsc() throws SQLException {
        UsefulArticles usefulArticles = null;
        List<UsefulArticles> usefulArticlesList = new ArrayList<>();
        ResultSet set = null;
        Connection connection = null;
        Statement statment = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `userfularticlescontroller` Order by `UAId` DESC LIMIT 4";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            UsefullArticlesList(usefulArticlesList, set);
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
        return usefulArticlesList;
    }


    /**
     * Update useful Articles Armenian
     *
     * @param UsefullArticlesArmenian
     * @param UsefulArticlesId
     * @return
     */
    public int UpdateUsefulArticlesArmenian(String UsefullArticlesTitleArmenian, String UsefullArticlesArmenian, int UsefulArticlesId) throws SQLException {
        int rowsUpdated = 0;

        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `userfularticlescontroller`  SET UAMainTextAm=?,UATitleAm=? WHERE UAId=" + UsefulArticlesId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, UsefullArticlesArmenian);
            statment.setString(2, UsefullArticlesTitleArmenian);
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
     * Update useful Articles Armenian
     *
     * @param UsefullArticlesEnglish
     * @param UsefulArticlesId
     * @return
     */
    public int UpdateUsefulArticlesEnglish(String UsefullArticlesTitleEnglish, String UsefullArticlesEnglish, int UsefulArticlesId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `userfularticlescontroller`  SET UAMainTextEn=?,UATitleEn=? WHERE UAId=" + UsefulArticlesId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, UsefullArticlesEnglish);
            statment.setString(2, UsefullArticlesTitleEnglish);
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
     * Get Blog in Armenian language by id
     *
     * @param uaId
     * @return
     */
    public List<UsefulArticles> getUsefulArmenianArticleById(int uaId) throws SQLException {
        UsefulArticles usefulArticles = null;
        List<UsefulArticles> usefulArticlesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT *  FROM  `userfularticlescontroller` WHERE UAId=" + uaId;
            statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                usefulArticles = new UsefulArticles();
                usefulArticles.setUAId(set.getInt("UAId"));
                usefulArticles.setUATitleAm(set.getString("UATitleAm"));
                usefulArticles.setUAMainTextAm(set.getString("UAMainTextAm"));
                usefulArticles.setUAImageLink(set.getString("UAImageLink"));

                usefulArticlesList.add(usefulArticles);
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

        }
        return usefulArticlesList;
    }


    /**
     * Get Blog in Russian language by id
     *
     * @param uaId
     * @return
     */
    public List<UsefulArticles> getUsefulRussianArticleById(int uaId) throws SQLException {
        UsefulArticles usefulArticles = null;
        List<UsefulArticles> usefulArticlesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT *  FROM  `userfularticlescontroller` WHERE UAId=" + uaId;
            statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                usefulArticles = new UsefulArticles();
                usefulArticles.setUAId(set.getInt("UAId"));
                usefulArticles.setUATitleRu(set.getString("UATitleRu"));
                usefulArticles.setUAMainTextRu(set.getString("UAMainTextRu"));
                usefulArticles.setUAImageLink(set.getString("UAImageLink"));

                usefulArticlesList.add(usefulArticles);
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

        }
        return usefulArticlesList;
    }

    /**
     * Get Blog in English language by id
     *
     * @param uaId
     * @return
     */
    public List<UsefulArticles> getUsefulEnglishArticleById(int uaId) throws SQLException {
        UsefulArticles usefulArticles = null;
        List<UsefulArticles> usefulArticlesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        try {
             connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT *  FROM  `userfularticlescontroller` WHERE UAId=" + uaId;
             statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                usefulArticles = new UsefulArticles();
                usefulArticles.setUAId(set.getInt("UAId"));
                usefulArticles.setUATitleEn(set.getString("UATitleEn"));
                usefulArticles.setUAMainTextEn(set.getString("UAMainTextEn"));
                usefulArticles.setUAImageLink(set.getString("UAImageLink"));

                usefulArticlesList.add(usefulArticles);
            }

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

        }
        return usefulArticlesList;
    }


    public List<UsefulArticles> getAllUsefullArticlesById(int bolgId) throws SQLException {
        UsefulArticles usefulArticles = null;
        List<UsefulArticles> usefulArticlesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        try {
             connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT *  FROM  `userfularticlescontroller` WHERE UAId=" + bolgId;
             statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                usefulArticles = new UsefulArticles();
                usefulArticles.setUAId(set.getInt("UAId"));
                usefulArticles.setUATitleEn(set.getString("UATitleEn"));
                usefulArticles.setUATitleRu(set.getString("UATitleRu"));
                usefulArticles.setUATitleAm(set.getString("UATitleAm"));
                usefulArticles.setUAMainTextEn(set.getString("UAMainTextEn"));
                usefulArticles.setUAMainTextRu(set.getString("UAMainTextRu"));
                usefulArticles.setUAMainTextAm(set.getString("UAMainTextAm"));
                usefulArticles.setUAImageLink(set.getString("UAImageLink"));

                usefulArticlesList.add(usefulArticles);
            }

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

        }
        return usefulArticlesList;
    }

    /**
     * Update usefull articles in Russian
     *
     * @param UsefullArticlesTitleRussian
     * @param UsefullArticlesRussian
     * @param UsefulArticlesId
     * @return
     */
    public int UpdateUsefulArticlesRussian(String UsefullArticlesTitleRussian, String UsefullArticlesRussian, int UsefulArticlesId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
             connection = connectToData();
            String sql = "UPDATE `userfularticlescontroller`  SET UAMainTextRu=?,UATitleRu=? WHERE UAId=" + UsefulArticlesId;
             statment = connection.prepareStatement(sql);
            statment.setString(1, UsefullArticlesRussian);
            statment.setString(2, UsefullArticlesTitleRussian);
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
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
     * Delete Useful Article
     *
     * @param articleID
     * @return
     */
    public int DeleteArticle(int articleID) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
             connection = connectToData();
            String sql = "DELETE FROM `userfularticlescontroller` WHERE UAId=" + articleID;
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
     * Adding new Userfull Articles
     *
     * @param usefulArticles
     * @return
     * @throws SQLException
     */
    public int AddNewUsefulArticle(UsefulArticles usefulArticles) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
             connection = connectToData();
            String insertQuery = "INSERT INTO `userfularticlescontroller`" +
                    "(`UAId`, `UATitleAm`, `UATitleEn`, `UATitleRu`,`UAMainTextAm`,`UAMainTextEn`,`UAMainTextRu`,`date`) "
                    + "VALUES (Default,?,?,?,?,?,?,?)";
             statment = connection.prepareStatement(insertQuery);
            setStatment(usefulArticles, statment);
            rowsAffected = statment.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
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
     * Adding new article image
     *
     * @param usefulArticles
     * @param ArticleId
     * @return
     */
    public int AddNewUsefulArticleImage(UsefulArticles usefulArticles, int ArticleId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
             connection = connectToData();
            String sql = "UPDATE `userfularticlescontroller`  SET UAImageLink=? WHERE UAId=" + ArticleId;
             statment = connection.prepareStatement(sql);
            statment.setString(1, usefulArticles.getUAImageLink());
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
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
     * Setting statment for new Articles
     *
     * @param usefulArticles
     * @param statement
     * @throws SQLException
     */
    private void setStatment(UsefulArticles usefulArticles, PreparedStatement statement) throws SQLException {
        statement.setString(1, usefulArticles.getUATitleAm());
        statement.setString(2, usefulArticles.getUATitleEn());
        statement.setString(3, usefulArticles.getUATitleRu());
        statement.setString(4, usefulArticles.getUAMainTextAm());
        statement.setString(5, usefulArticles.getUAMainTextEn());
        statement.setString(6, usefulArticles.getUAMainTextRu());
        statement.setDate(7,usefulArticles.getDate());

    }


    private void UsefullArticlesList(List<UsefulArticles> usefulArticlesList, ResultSet set) throws SQLException {
        UsefulArticles usefulArticles;
        while (set.next()) {
            usefulArticles = new UsefulArticles();
            usefulArticles.setUAId(set.getInt("UAId"));
            usefulArticles.setUATitleAm(set.getString("UATitleAm"));
            usefulArticles.setUATitleEn(set.getString("UATitleEn"));
            usefulArticles.setUATitleRu(set.getString("UATitleRu"));
            usefulArticles.setUAMainTextAm(set.getString("UAMainTextAm"));
            usefulArticles.setUAMainTextEn(set.getString("UAMainTextEn"));
            usefulArticles.setUAMainTextRu(set.getString("UAMainTextru"));
            usefulArticles.setUAImageLink(set.getString("UAImageLink"));

            usefulArticlesList.add(usefulArticles);
        }
    }


    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }


}
