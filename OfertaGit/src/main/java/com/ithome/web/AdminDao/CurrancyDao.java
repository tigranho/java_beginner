package com.ithome.web.AdminDao;

import com.ithome.web.Bean.CardTypes;
import com.ithome.web.Bean.Currancy;
import com.ithome.web.Connection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrancyDao {

    /**
     * Show all cards
     */
    public List<Currancy> getAllCurrancyList() throws SQLException {
        Currancy currancy = null;
        List<Currancy> currancyList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `currancycontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCardsType(currancyList, set);
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
        return currancyList;
    }

    private void ListOfCardsType(List<Currancy> cardTypesList, ResultSet set) throws SQLException {
        Currancy currancy;
        while (set.next()) {
            currancy = new Currancy();
            currancy.setCurrancyId(set.getInt("currancyId"));
            currancy.setCurrancy(set.getString("currancy"));
            cardTypesList.add(currancy);
        }
    }
}
