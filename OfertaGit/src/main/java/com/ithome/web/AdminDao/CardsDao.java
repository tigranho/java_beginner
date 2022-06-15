package com.ithome.web.AdminDao;

import com.ithome.web.Bean.Cards;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardsDao {

    /**
     * Deleting cards
     *
     * @param cardId
     * @return
     */
    public int DeleteCard(int cardId) throws SQLException {
        PreparedStatement statment = null;
        Connection connection = null;
        int rowsDeleted = 0;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `cardcontroller` WHERE id=" + cardId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Message was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        } finally {
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
     * Show all cards
     */
    public List<Cards> getAllCardsList() throws SQLException {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<Cards>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(cardsList, set);
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
        return cardsList;
    }

    /**
     * Get cards by id
     *
     * @param cardId
     * @return
     */
    public List<Cards> getCardsById(int cardId) throws SQLException {

        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE id=" + cardId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(cardsList, set);

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
        return cardsList;
    }

    /**
     * Get Card list by Card code
     *
     * @param productcode
     * @return
     */
    public List<Cards> getCardsByCardCode(int productcode) throws SQLException {

        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE productcode=" + productcode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(cardsList, set);

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
        return cardsList;
    }

    /**
     * get Cards by card type id
     *
     * @param cardtypeId
     * @return
     */
    public List<Cards> getCardsBycardTypeId(int cardtypeId) {

        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardtypeid=" + cardtypeId;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }


    /**
     * Get card by bank id
     *
     * @param bankId
     * @return
     */
    public List<Cards> getCardsBybankId(int bankId) {

        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE bankid=" + bankId;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get card by card Min Service Fee
     *
     * @param cardMinServiceFee
     * @return
     */
    public List<Cards> getCardsBycardMinServiceFee(int cardMinServiceFee) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardminservicefee=" + cardMinServiceFee;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get card by card max Service Fee
     *
     * @param cardMaxnServiceFee
     * @return
     */
    public List<Cards> getCardsByCardMaxServiceFee(int cardMaxnServiceFee) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardmaxservicefee=" + cardMaxnServiceFee;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Cards by Card per minimum cash back
     *
     * @param cardPerMinCashBack
     * @return
     */
    public List<Cards> getCardsByCardPerMinCashBack(double cardPerMinCashBack) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardpermincashback=" + cardPerMinCashBack;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Cards by Card per maximum cash back
     *
     * @param cardPerMaxCashBack
     * @return
     */
    public List<Cards> getCardsByCardPerMaxCashBack(double cardPerMaxCashBack) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardpermaxcashback=" + cardPerMaxCashBack;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Cards by Card per maximum Discount
     *
     * @param cardPerMaxDiscount
     * @return
     */
    public List<Cards> getCardsByCardPerMaxDiscount(double cardPerMaxDiscount) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardpermaxdiscount=" + cardPerMaxDiscount;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Cards by Card per minimum Discount
     *
     * @param cardPerMinDiscount
     * @return
     */
    public List<Cards> getCardsByCardPerMinDiscount(double cardPerMinDiscount) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardpermindiscount=" + cardPerMinDiscount;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Cards by Card per minimum Credit limit
     *
     * @param cardMinCreditLimit
     * @return
     */
    public List<Cards> getCardsByCardMinCreditLimit(double cardMinCreditLimit) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardmincreditlimit=" + cardMinCreditLimit;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Cards by Card per maximum Credit limit
     *
     * @param cardMaxCreditLimit
     * @return
     */
    public List<Cards> getCardsByCardMaxCreditLimit(double cardMaxCreditLimit) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardmaxcreditlimit=" + cardMaxCreditLimit;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Cards by Card per Credit limit
     *
     * @param cardPerCreditLimit
     * @return
     */
    public List<Cards> getCardsByCardPerCreditLimit(double cardPerCreditLimit) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardpercreditlimit=" + cardPerCreditLimit;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * gert card by Card per factual
     *
     * @param cardFactual
     * @return
     */
    public List<Cards> getCardsByCardPerFactual(double cardFactual) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardfactual=" + cardFactual;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get card by card grace period
     *
     * @param cardGracePeriod
     * @return
     */
    public List<Cards> getCardsByCardGracePeriod(double cardGracePeriod) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardgraceperiod=" + cardGracePeriod;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Card by Card percentage on credit standing
     *
     * @param cardPerOnCreditStanding
     * @return
     */
    public List<Cards> getCardsByCardPerOnCreditStanding(double cardPerOnCreditStanding) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE cardperoncreditstanding=" + cardPerOnCreditStanding;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * get Card by productCode
     *
     * @param productCode
     * @return
     */
    public List<Cards> getCardsByProductCode(int productCode) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `cardcontroller` WHERE productCode=" + productCode;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return cardsList;
    }

    /**
     * load cards with bank name
     *
     * @param bankName
     * @return
     */
    public List<Cards> LoadCardsBybankName(String bankName) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` where `bankname` like '%" + bankName + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return cardsList;
    }

    /**
     * load card by bank web site
     *
     * @param bankWebSite
     * @return
     */
    public List<Cards> LoadCardsBybankWebSite(String bankWebSite) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` where `bankwebsite` like '%" + bankWebSite + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return cardsList;
    }

    /**
     * load card by card name
     *
     * @param cardName
     * @return
     */
    public List<Cards> LoadCardsByCardName(String cardName) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` where `cardname` like '%" + cardName + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return cardsList;
    }

    /**
     * load card by card type
     *
     * @param cardType
     * @return
     */
    public List<Cards> LoadCardsByCardType(String cardType) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` where `cardtype` like '%" + cardType + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return cardsList;
    }

    /**
     * load card by card currency
     *
     * @param Currency
     * @return
     */
    public List<Cards> LoadCardsByCardCurrency(String Currency) {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` where `currency` like '%" + Currency + "%'";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(cardsList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return cardsList;
    }

    /**
     * cards order by order on appeaarance
     *
     * @return
     */
    public List<Cards> cardsOrderByCardOrderOnAppearance() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  orderonappearance DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * Cards order by card id
     *
     * @return
     */
    public List<Cards> CardsOrderByCardId() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  id DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * Cards order by card Code
     *
     * @return
     */
    public List<Cards> CardsOrderByCardCode() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  productcode DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * Card order by bank name
     *
     * @return
     */
    public List<Cards> CardsOrderByBankName() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  bankname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by card Name
     *
     * @return
     */
    public List<Cards> CardsOrderByCardName() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardTypeId
     *
     * @return
     */
    public List<Cards> CardsOrderByCardTypeId() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardtypeid DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }


    /**
     * card order by cardType
     *
     * @return
     */
    public List<Cards> CardsOrderByCardType() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardtype DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardMinServiceFee
     *
     * @return
     */
    public List<Cards> CardsOrderByCardMinServiceFee() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardminservicefee DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardMaxServiceFee
     *
     * @return
     */
    public List<Cards> CardsOrderByCardMaxServiceFee() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardmaxservicefee DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardPerMinCashBack
     *
     * @return
     */
    public List<Cards> CardsOrderByCardPerMinCashBack() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardpermincashback DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardPerMaxCashBack
     *
     * @return
     */
    public List<Cards> CardsOrderByCardPerMaxCashBack() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardpermaxcashback DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }


    /**
     * card order by cardPerMinDiscount
     *
     * @return
     */
    public List<Cards> CardsOrderByCardPerMinDiscount() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardpermindiscount DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardPerMaxDiscount
     *
     * @return
     */
    public List<Cards> CardsOrderByCardPerMaxDiscount() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardpermaxdiscount DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardMinCreditLimit
     *
     * @return
     */
    public List<Cards> CardsOrderByCardMinCreditLimit() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardmincreditlimit DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * Adding new Cards
     *
     * @param cards
     * @return
     * @throws SQLException
     */
    public int AddNewCard(Cards cards) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `cardcontroller`" +
                    "(`id`, `productcode`, `bankid`, `bankname`,`bankwebsite`,`cardname`," +
                    "`cardimage`,`cardminservicefee`, `cardmaxservicefee`, `cardpermincashback`,`cardpermaxcashback`," +
                    "`cardpermindiscount`,`cardpermaxdiscount`,`cardmincreditlimit`, `cardmaxcreditlimit`,`cardpercreditlimit`," +
                    "`cardperfactual`,`cardgraceperiod`,`currancy`, `minage`,`maxage`,`cardperoncreditstanding`," +
                    "`orderonappearance`, `specialofferid`,`Searchpositionid`,`sendrequest`,`LastLogic`,`mincashback`," +
                    "`maxcashback`,`BankLink`,`details`,`cardtypecredit`,`cardTypefree`,`cardtypedebit`,`cardtypecashback`,`cardtypegrace`,`cardinfo`,`LastLink`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(cards, statment);
            rowsAffected = statment.executeUpdate();
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

        return rowsAffected;

    }

    /**
     * SetStatment
     *
     * @param cards
     * @param statement
     * @throws SQLException
     */
    private void setStatment(Cards cards, PreparedStatement statement) throws SQLException {

        statement.setLong(1, cards.getProductCode());
        statement.setInt(2, cards.getBankId());
        statement.setString(3, cards.getBankName());
        statement.setString(4, cards.getBankWebSite());
        statement.setString(5, cards.getCardName());
        statement.setString(6, cards.getCardImage());

        statement.setInt(7, cards.getCardMinServiceFee());
        statement.setInt(8, cards.getCardMaxServiceFee());
        statement.setDouble(9, cards.getCardPerMinCashBack());
        statement.setDouble(10, cards.getCardPerMaxCashBack());
        statement.setDouble(11, cards.getCardPerMinDiscount());
        statement.setDouble(12, cards.getCardPerMaxDiscount());
        statement.setInt(13, cards.getCardMinCreditLimit());
        statement.setInt(14, cards.getCardMaxCreditLimit());
        statement.setDouble(15, cards.getCardPerCreditLimit());

        statement.setDouble(16, cards.getCardPerFactual());
        statement.setInt(17, cards.getCardGracePeriod());
        statement.setString(18, cards.getCurrancy());
        statement.setInt(19, cards.getMinAge());
        statement.setInt(20, cards.getMaxAge());
        statement.setDouble(21, cards.getCardPerOnCreditStanding());

        statement.setInt(22, cards.getOrderOfAppearance());
        statement.setInt(23, cards.getSpecialOffer());
        statement.setInt(24, cards.getFirstSearchList());
        statement.setInt(25, cards.getSendRequest());
        statement.setInt(26, cards.getLastlogic());

        statement.setDouble(27, cards.getMinCashBack());
        statement.setDouble(28, cards.getMaxCashBack());
        statement.setString(29, cards.getBankLink());
        statement.setString(30, cards.getDetails());
/*
,,=?,=?,=?,=?," +
                    "=?,=?,=?,=?,=?,=?,=?" +
                    ",=?,=?,=?,=?,=?,=?,=?,=?," +
                    "=?,=?,=?,=?,=?,=?,=?,=?,=?," +
                    "=?,=?,=?,=?,=?,cardinfo=?
 */
        statement.setString(31, cards.getCrediting());
        statement.setString(32, cards.getFree());
        statement.setString(33, cards.getDebit());
        statement.setString(34, cards.getCashback());
        statement.setString(35, cards.getTimer());

        statement.setString(36, cards.getCardInfo());
        statement.setString(37, cards.getLastLink());

    }

    /**
     * card order by cardMaxCreditLimit
     *
     * @return
     */
    public List<Cards> CardsOrderByCardMaxCreditLimit() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardmaxcreditlimit DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardPerCreditLimit
     *
     * @return
     */
    public List<Cards> CardsOrderByCardPerCreditLimit() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardpercreditlimit DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardPerFactual
     *
     * @return
     */
    public List<Cards> CardsOrderByCardFactual() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardperfactual DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardGracePeriod
     *
     * @return
     */
    public List<Cards> CardsOrderByCardGracePeriod() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardgraceperiod DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardCurrency
     *
     * @return
     */
    public List<Cards> CardsOrderByCardCurrency() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  currancy DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardPerOnCreditStanding
     *
     * @return
     */
    public List<Cards> CardsOrderByCardPerOnCreditStanding() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  cardperoncreditstanding DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }


    public List<Cards> getCardsByOrderOfApperance() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  orderonappearance ASC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                cards = new Cards();
                cards.setBankId(set.getInt("bankId"));
                cards.setBankName(set.getString("bankName"));
                cards.setBankWebSite(set.getString("bankWebSite"));
                cards.setCardId(set.getLong("id"));
                cards.setProductCode(set.getInt("productCode"));
                cards.setCardName(set.getString("cardName"));
                cards.setCardTypeId(set.getLong("cardTypeId"));
                cards.setCardType(set.getString("cardType"));
                cards.setCardImage(set.getString("cardImage"));
                cards.setCardMinServiceFee(set.getInt("cardMinServiceFee"));
                cards.setCardMaxServiceFee(set.getInt("cardMaxServiceFee"));
                cards.setCardPerMinCashBack(set.getDouble("cardPerMinCashBack"));
                cards.setCardPerMaxCashBack(set.getDouble("cardPerMaxCashBack"));
                cards.setCardPerMinDiscount(set.getDouble("cardPerMinDiscount"));
                cards.setCardPerMaxDiscount(set.getDouble("cardPerMaxDiscount"));
                cards.setCardMinCreditLimit(set.getInt("cardMinCreditLimit"));
                cards.setCardMaxCreditLimit(set.getInt("cardMaxCreditLimit"));
                cards.setCardPerCreditLimit(set.getDouble("cardPerCreditLimit"));
                cards.setCardPerFactual(set.getDouble("cardPerFactual"));
                cards.setCardGracePeriod(set.getInt("cardGracePeriod"));
                cards.setCurrancy(set.getString("currancy"));
                cards.setMinAge(set.getInt("minAge"));
                cards.setMaxAge(set.getInt("maxAge"));
                cards.setCardPerOnCreditStanding(set.getDouble("cardPerOnCreditStanding"));
                cards.setOrderOfAppearance(set.getInt("OrderOnAppearance"));
                cards.setSpecialOffer(set.getInt("SpecialOfferId"));
                cards.setFirstSearchList(set.getInt("SearchPositionId"));
                cards.setDetails(set.getString("details"));
                cards.setSendRequest(set.getInt("SendRequest"));


                cardsList.add(cards);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;
    }

    /**
     * card order by cardSpecialOfferId
     *
     * @return
     */
    public List<Cards> CardsOrderByCardSpecialOfferId() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  specialofferid DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardSearchPositionId
     *
     * @return
     */
    public List<Cards> CardsOrderByCardSearchPositionId() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  Searchpositionid DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * card order by cardSendRequest
     *
     * @return
     */
    public List<Cards> CardsOrderByCardSendRequest() {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` ORDER BY  sendrequest DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(cardsList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cardsList;

    }

    /**
     * Add or update image of card in database
     *
     * @param cards
     * @param cardId
     * @return
     */
    public int UpdateCardImageInData(Cards cards, int cardId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cardcontroller`  SET cardimage=?  WHERE productcode=" + cardId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, cards.getCardImage());

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

    public int UpdateCardImageInDataById(Cards cards, int cardId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cardcontroller`  SET cardimage=?  WHERE id=" + cardId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, cards.getCardImage());

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

    public int UpdateCardPDFInDataById(Cards cards, int cardId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cardcontroller`  SET pdf=?  WHERE id=" + cardId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, cards.getCardImage());

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

    public int UpdateCardPDFSalesInDataById(Cards cards, int cardId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cardcontroller`  SET pdf2=?  WHERE id=" + cardId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, cards.getCardImage());

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
     * List cards in Set
     *
     * @param cardsList
     * @param set
     */
    private void ListOfCards(List<Cards> cardsList, ResultSet set) throws SQLException {
        Cards cards;
        while (set.next()) {
            cards = new Cards();
            cards.setBankId(set.getInt("bankId"));
            cards.setBankName(set.getString("bankName"));
            cards.setBankWebSite(set.getString("bankWebSite"));
            cards.setCardId(set.getLong("id"));
            cards.setProductCode(set.getInt("productCode"));
            cards.setCardName(set.getString("cardName"));
            cards.setCardImage(set.getString("cardImage"));
            cards.setCardMinServiceFee(set.getInt("cardMinServiceFee"));
            cards.setCardMaxServiceFee(set.getInt("cardMaxServiceFee"));
            cards.setCardPerMinCashBack(set.getDouble("cardPerMinCashBack"));
            cards.setCardPerMaxCashBack(set.getDouble("cardPerMaxCashBack"));
            cards.setCardPerMinDiscount(set.getDouble("cardPerMinDiscount"));
            cards.setCardPerMaxDiscount(set.getDouble("cardPerMaxDiscount"));
            cards.setCardMinCreditLimit(set.getInt("cardMinCreditLimit"));
            cards.setCardMaxCreditLimit(set.getInt("cardMaxCreditLimit"));
            cards.setCardPerCreditLimit(set.getDouble("cardPerCreditLimit"));
            cards.setCardPerFactual(set.getDouble("cardPerFactual"));
            cards.setCardGracePeriod(set.getInt("cardGracePeriod"));
            cards.setCurrancy(set.getString("currancy"));
            cards.setMinAge(set.getInt("minAge"));
            cards.setMaxAge(set.getInt("maxAge"));
            cards.setCardPerOnCreditStanding(set.getDouble("cardPerOnCreditStanding"));
            cards.setOrderOfAppearance(set.getInt("OrderOnAppearance"));
            cards.setSpecialOffer(set.getInt("SpecialOfferId"));
            cards.setFirstSearchList(set.getInt("SearchPositionId"));
            cards.setSendRequest(set.getInt("SendRequest"));
            cards.setLastlogic(set.getInt("LastLogic"));
            cards.setMinCashBack(set.getDouble("minCashBack"));
            cards.setMaxCashBack(set.getDouble("maxCashBack"));
            cards.setDetails(set.getString("details"));
            cards.setBankLink(set.getString("bankLink"));

            cards.setCrediting(set.getString("cardtypecredit"));
            cards.setFree(set.getString("cardTypefree"));
            cards.setDebit(set.getString("cardtypedebit"));
            cards.setCashback(set.getString("cardtypecashback"));
            cards.setTimer(set.getString("cardtypegrace"));

            cards.setPDF(set.getString("pdf"));
            cards.setPDFSale(set.getString("pdf2"));
            cards.setCardInfo(set.getString("cardinfo"));
            cards.setLastLink(set.getString("LastLink"));
            cards.setBanksList(cardsList);
            cardsList.add(cards);
        }
    }


    public int UpdateCardDetailsinData(Cards prepaireCardInfoForData, int cardId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;

        try {
            connection = connectToData();
            String sql = "UPDATE `cardcontroller`  SET productcode=?,bankid=?,bankname=?,bankwebsite=?,cardname=?,cardImage=?," +
                    "cardMinServiceFee=?,cardMaxservicefee=?,cardpermincashback=?,cardpermaxcashback=?,cardpermindiscount=?,cardpermaxdiscount=?,cardmincreditlimit=?" +
                    ",cardmaxcreditlimit=?,cardpercreditlimit=?,cardperfactual=?,cardgraceperiod=?,currancy=?,minage=?,maxage=?,cardperoncreditstanding=?," +
                    "orderonappearance=?,specialofferid=?,Searchpositionid=?,sendrequest=?,LastLogic=?,mincashback=?,maxcashback=?,BankLink=?,details=?," +
                    "cardtypecredit=?,cardTypefree=?,cardtypedebit=?,cardtypecashback=?,cardtypegrace=?,cardinfo=?,LastLink=? WHERE id=" + cardId;

            statment = connection.prepareStatement(sql);
            setStatment(prepaireCardInfoForData, statment);

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


    public List<Cards> getCardsWithSpecialOffer() throws SQLException {
        Cards cards = null;
        List<Cards> cardsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` WHERE `specialofferid`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(cardsList, set);
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
        return cardsList;
    }

    public List<Cards> getDepositbyMaxMinAmountById(int minAmount, String currancy, int BankId) throws SQLException {

        Cards deposit = null;
        List<Cards> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `cardmincreditlimit` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(depositList, set);
        } catch (SQLException exception) {
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
        return depositList;
    }

    public List<Cards> getDepositbyMaxMinAmountAsec(int minAmount, String currancy) throws SQLException {
        Cards mortgage = null;
        List<Cards> mortgageList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` WHERE `orderonappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `cardmincreditlimit`<= " + minAmount + " ORDER BY `cardmincreditlimit` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(mortgageList, set);
        } catch (SQLException exception) {
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
        return mortgageList;
    }

    public List<Cards> getDepositMaxMinPercentByID(int minAmount, String currancy, int BankId) throws SQLException {
        Cards deposit = null;
        List<Cards> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `cardperfactual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(depositList, set);
        } catch (SQLException exception) {
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
        return depositList;
    }

    public List<Cards> getDepositMaxMinPercentSubAcs(int minAmount, String currancy) throws SQLException {
        Cards mortgage = null;
        List<Cards> mortgageList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cardcontroller` WHERE `orderonappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `cardmincreditlimit`<= " + minAmount + " ORDER BY `cardperfactual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(mortgageList, set);
        } catch (SQLException exception) {
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
        return mortgageList;
    }

    /**
     * Add or update image of card in database
     *
     * @param cards
     * @param cardId
     * @return
     */
    public int UpdateCardPDFInData(Cards cards, int cardId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cardcontroller`  SET pdf=?  WHERE productcode=" + cardId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, cards.getCardImage());

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
     * Add or update image of card in database
     *
     * @param cards
     * @param cardId
     * @return
     */
    public int UpdateCardPDFInDataSales(Cards cards, int cardId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cardcontroller`  SET pdf2=?  WHERE productcode=" + cardId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, cards.getCardImage());

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

}

