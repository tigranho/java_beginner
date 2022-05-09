package com.ithome.web.AdminDao;

import com.ithome.web.Bean.AboutUs;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AboutUsDao {

    /**
     * Show all about us
     *
     * @return
     */
    public List<AboutUs> getAllAboutUs() throws SQLException {

        AboutUs aboutUs = null;
        List<AboutUs> aboutList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `aboutus` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            aboutList(aboutList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return aboutList;
    }

    /**
     * Add new about us
     *
     * @param aboutUs
     * @return
     */
    public int AddAboutUS(AboutUs aboutUs) {
        int rowsAffected = 0;
        try {
            Connection connection = connectToData();
            String insertQuery = "INSERT INTO `aboutus`(`aboutusid`, `aboutus_Arm`, `aboutus_Ru`, `aboutus_Eng`)" +
                    " Values(Default,?,?,?)";
            PreparedStatement statment = connection.prepareStatement(insertQuery);
            statment.setString(1, aboutUs.getAbout_arm());
            statment.setString(2, aboutUs.getAbout_Eng());
            statment.setString(3, aboutUs.getAbout_Ru());

            rowsAffected = statment.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * get about us By Id
     *
     * @param aboutusId
     * @return
     * @throws SQLException
     */
    public List<AboutUs> getAboutusByID(int aboutusId) throws SQLException {
        AboutUs aboutUs = null;
        List<AboutUs> aboutusList = new ArrayList<>();
        Connection connection = connectToData();
        String sql = "SELECT * FROM `aboutus` Where aboutusid=" + aboutusId;
        Statement statment = connection.createStatement();
        ResultSet set = statment.executeQuery(sql);
        while (set.next()) {
            aboutUs = new AboutUs();
            aboutList(aboutusList, set);
        }
        return aboutusList;
    }

    /**
     * get Russian about us
     *
     * @return
     */
    public List<AboutUs> getAboutUsInRussian() throws SQLException {
        AboutUs aboutUs = null;
        List<AboutUs> aboutusList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `aboutus` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            aboutUsRus(aboutusList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return aboutusList;
    }

    /**
     * get English about us
     *
     * @return
     */
    public List<AboutUs> getAboutUsInEnglish() throws SQLException {
        AboutUs aboutUs = null;
        List<AboutUs> aboutusList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `aboutus` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            aboutUsEng(aboutusList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return aboutusList;
    }

    /**
     * get Armenian about us
     *
     * @return
     */
    public List<AboutUs> getAboutUsInArmenian() throws SQLException {
        AboutUs aboutUs = null;
        List<AboutUs> aboutusList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `aboutus` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            aboutUsArm(aboutusList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return aboutusList;
    }

    /**
     * Get russian about us
     *
     * @param aboutUsList
     * @param set
     * @throws SQLException
     */
    private void aboutUsArm(List<AboutUs> aboutUsList, ResultSet set) throws SQLException {
        AboutUs aboutUs;
        while (set.next()) {
            aboutUs = new AboutUs();
            aboutUs.setAboutId(set.getInt("aboutusid"));
            aboutUs.setAbout_arm(set.getString("aboutus_Arm"));
            aboutUsList.add(aboutUs);
        }
    }

    /**
     * Get russian about us
     *
     * @param aboutUsList
     * @param set
     * @throws SQLException
     */
    private void aboutUsRus(List<AboutUs> aboutUsList, ResultSet set) throws SQLException {
        AboutUs aboutUs;
        while (set.next()) {
            aboutUs = new AboutUs();
            aboutUs.setAboutId(set.getInt("aboutusid"));
            aboutUs.setAbout_Ru(set.getString("aboutus_Ru"));
            aboutUsList.add(aboutUs);
        }
    }

    /**
     * Get English about us
     *
     * @param aboutUsList
     * @param set
     * @throws SQLException
     */
    private void aboutUsEng(List<AboutUs> aboutUsList, ResultSet set) throws SQLException {
        AboutUs aboutUs;
        while (set.next()) {
            aboutUs = new AboutUs();
            aboutUs.setAboutId(set.getInt("aboutusid"));
            aboutUs.setAbout_Eng(set.getString("aboutus_Eng"));
            aboutUsList.add(aboutUs);
        }
    }


    /**
     * Set of abouts
     *
     * @param aboutList
     * @param set
     * @throws SQLException
     */
    private void aboutList(List<AboutUs> aboutList, ResultSet set) throws SQLException {
        AboutUs aboutUs;
        while (set.next()) {
            aboutUs = new AboutUs();
            aboutUs.setAboutId(set.getInt("aboutusid"));
            aboutUs.setAbout_arm(set.getString("aboutus_Arm"));
            aboutUs.setAbout_Ru(set.getString("aboutus_Ru"));
            aboutUs.setAbout_Eng(set.getString("aboutus_Eng"));

            aboutList.add(aboutUs);
        }
    }

    /**
     * Update Aboutus in English
     *
     * @param aboutUsEng
     * @param aboutUsId
     * @return
     */
    public int UpdateAboutusEng(String aboutUsEng, int aboutUsId) {
        int rowsUpdated = 0;
        try {
            Connection connection = connectToData();

            String sql = "UPDATE `aboutus`  SET aboutus_Eng=? WHERE aboutusid=" + aboutUsId;
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, aboutUsEng);
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rowsUpdated;
    }


    /**
     * Update Aboutus in Russian
     *
     * @param aboutUsRu
     * @param aboutUsId
     * @return
     */
    public int UpdateAboutusRu(String aboutUsRu, int aboutUsId) {
        int rowsUpdated = 0;
        try {
            Connection connection = connectToData();

            String sql = "UPDATE `aboutus`  SET aboutus_Ru=? WHERE aboutusid=" + aboutUsId;
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, aboutUsRu);
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rowsUpdated;
    }


    /**
     * Update Aboutus in Armenian
     *
     * @param aboutUsAm
     * @param aboutUsId
     * @return
     */
    public int UpdateAboutusArm(String aboutUsAm, int aboutUsId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `aboutus`  SET aboutus_Arm=? WHERE aboutusid=" + aboutUsId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, aboutUsAm);
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

}
