package com.ithome.web.AdminDao;

import com.ithome.web.Bean.TimeLine;
import com.ithome.web.Connection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TimeLineDao {

    /**
     * Show all about us
     * @return
     */
    public List<TimeLine> getAllTimeLine() throws SQLException {
        TimeLine timeLine= null;
        List<TimeLine> timeLineList = new ArrayList<>();
        ResultSet set = null;
        Connection connection = null;
        Statement statment = null;
        try {
             connection = connectToData();
            String sql = "SELECT * FROM `timeline` ";
             statment = connection.createStatement();
             set = statment.executeQuery(sql);
            TimeLineList(timeLineList, set);
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
        return timeLineList;
    }

    private void TimeLineList(List<TimeLine> timeLineList, ResultSet set) throws SQLException {
        TimeLine timeLine;
        while (set.next()) {
            timeLine = new TimeLine();
            timeLine.setId(set.getInt("id"));
            timeLine.setTime(set.getInt("time"));
            timeLineList.add(timeLine);
        }
    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }
}
