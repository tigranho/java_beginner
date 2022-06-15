package com.ithome.web.AdminDao;

import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.CardTypes;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardTypeDao {
    /**
     * Show all cards
     */
    public List<CardTypes> getAllCardsList() throws SQLException {
        CardTypes cardTypes = null;
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        List<CardTypes> CardTypesList = new ArrayList<>();
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cardtypecontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCardsType(CardTypesList, set);
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
        return CardTypesList;
    }

    private void ListOfCardsType(List<CardTypes> cardTypesList, ResultSet set) throws SQLException {
        CardTypes cardTypes;
        while (set.next()) {
            cardTypes = new CardTypes();
            cardTypes.setCardTypeid(set.getInt("cardtTypeid"));
            cardTypes.setCardType(set.getString("cardType"));
            cardTypesList.add(cardTypes);
        }
    }

    public List<CardTypes> getCardInfoByName(String cardType) throws SQLException {
        CardTypes cardTypes = null;
        List<CardTypes> cardTypesList = new ArrayList<>();
        Connection connection= null;
        PreparedStatement statment =null;
        ResultSet set =null;
        try {
             connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cardtypecontroller` where `cardType` like '%" + cardType + "%'";

             statment = connection.prepareStatement(sql);
             set = statment.executeQuery();
            ListOfCardsType(cardTypesList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }finally {
            if(connection != null){
                connection.close();
            }if(statment != null){
                statment.close();
            }if(set != null){
                set.close();
            }
        }
        return cardTypesList;
    }


}
