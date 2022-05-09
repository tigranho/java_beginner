package com.ithome.web.AdminDao;

import com.ithome.web.Bean.HitCounterBean;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CounterDaoController {
    LocalDate date = LocalDate.now();

    private Connection connectToData() {

        return DBConnection.getConnectionToDatabase();
    }


    public int Addcounter(HitCounterBean hitCounter) throws SQLException {
        PreparedStatement statment = null;
        Connection connection = null;
        int rowsAffected = 0;
        try {
            connectToData();
            connection = connectToData();
            String insertQuery = "INSERT INTO `staticcontroller`" +
                    "(`id`, `pagename`, `pageCurrancy`, `city`,`Language`,`dateandtime`,`sessionId`) "
                    + "VALUES (Default,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(hitCounter, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowsAffected;

    }

    private void setStatment(HitCounterBean hitCounterbean, PreparedStatement statement) throws SQLException {
        statement.setString(1, hitCounterbean.getName());
        statement.setString(2, hitCounterbean.getPageCurrancy());
        statement.setString(3, hitCounterbean.getCity());
        statement.setString(4, hitCounterbean.getPageLanguage());
        statement.setDate(5, Date.valueOf(date));
        statement.setString(6, hitCounterbean.getSessionId());
    }

    /**
     * Counting page visits
     *
     * @param pageName
     * @return
     */
    public int countPages(String pageName) throws SQLException {
        HitCounterBean hit;
        Statement statment = null;
        Connection connection = null;
        int count = 0;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `staticcontroller` WHERE pagename like '%" + pageName + "%'";
            statment = connection.createStatement();
            count = statment.executeUpdate(sql);
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
        }
        return count;
    }

    public List<HitCounterBean> getCountersDetail() throws SQLException {
        HitCounterBean HitCounterBean = null;
        List<HitCounterBean> hitCounterBeanList = new ArrayList<HitCounterBean>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `staticcontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfHiter(hitCounterBeanList, set);
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
        return hitCounterBeanList;

    }

    private void ListOfHiter(List<HitCounterBean> hitCounterBeanList, ResultSet set) throws SQLException {
        HitCounterBean hit;
        while (set.next()) {
            hit = new HitCounterBean();
            hit.setId(set.getInt("id"));
            hit.setName(set.getString("pagename"));
            hit.setCity(set.getString("city"));
            hit.setPageCurrancy(set.getString("pageCurrancy"));
            hit.setPageLanguage(set.getString("Language"));
            hitCounterBeanList.add(hit);
        }

    }
}
