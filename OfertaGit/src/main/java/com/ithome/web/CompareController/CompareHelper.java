package com.ithome.web.CompareController;


import com.ithome.web.Bean.Deposit;
import com.ithome.web.Comparision.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompareHelper {


    private static List<Integer> depositCompareList = new ArrayList<>();
    private static List<Integer> depositMortgagList = new ArrayList<>();
    private static List<Integer> depositConsumerList = new ArrayList<>();
    private static List<Integer> depositCarLoanList = new ArrayList<>();
    private static List<Integer> depositMicroList = new ArrayList<>();
    private static List<Integer> depositAgList = new ArrayList<>();
    private static List<Integer> depositCardsList = new ArrayList<>();
    private static String theId;


    public static void sendSession(String sessionId) {
        theId = sessionId;
    }

    public static boolean AddDepositToArrayById(int id) {
        return depositCompareList.add(id);

    }


    public static void AddAgToArrayById(int id) {
        depositAgList.add(id);
    }

    public static boolean AddMicroToArrayById(int id) {
        return depositMicroList.add(id);

    }

    public static boolean AddAutoToArrayById(int id) {
        return depositCarLoanList.add(id);

    }

    public static boolean AddConsumerToArrayById(int id) {
        return depositConsumerList.add(id);

    }

    public static boolean AddMortgageToArrayById(int id) {
        return depositMortgagList.add(id);

    }

    public static boolean DepositCheckOutOfBound() {
        return depositCompareList.size() > 3;
    }

    public static boolean AGCheckOutOfBound() {
        return depositAgList.size() > 3;
    }

    public static boolean MicroCheckOutOfBound() {
        return depositMicroList.size() > 3;
    }

    public static boolean AutoCheckOutOfBound() {
        return depositCarLoanList.size() > 3;
    }

    public static boolean ConsumerCheckOutOfBound() {
        return depositConsumerList.size() > 3;
    }

    public static boolean DepositCheckOutOfBoundMortgage() {
        return depositMortgagList.size() > 3;

    }


    public static boolean CheckIfIdExsist(int id) {
        for(int element : depositCompareList){
            if(element == id){
                return true;
            }
        }
        return false;
        /*for (int i = 0; i < depositCompareList.size(); i++) {
            return depositCompareList.get(i) == id;
        }
        return false;*/
    }

    public static boolean CheckIfIdExsistAG(int id) {
        for (int i = 0; i < depositAgList.size(); i++) {
           if(depositAgList.get(i) == id){
               return true;
            }
        }
        return false;
    }

    public static boolean CheckIfIdExsistMicro(int id) {
        for (int i = 0; i < depositMicroList.size(); i++) {
            if(depositMicroList.get(i) == id){
                return true;
            }
        }
        return false;
    }

    public static boolean CheckIfIdExsistAuto(int id) {
        for (int i = 0; i < depositCarLoanList.size(); i++) {
             if(depositCarLoanList.get(i) == id){
                 return true;
             }
        }
        return false;
    }

    public static boolean CheckIfIdExsistConsumer(int id) {
        for (int i = 0; i < depositConsumerList.size(); i++) {
             if(depositConsumerList.get(i) == id){
                 return true;
             }
        }
        return false;
    }

    public static boolean CheckIfIdExsistMortgage(int id) {
        for (int i = 0; i < depositMortgagList.size(); i++) {
            if(depositMortgagList.get(i) == id){
                return true;
            }
        }
        return false;
    }


    public static int GetSize() {
        return depositCompareList.size();

    }

    public static int GetSizeAG() {
        return depositAgList.size();
    }

    public static int GetSizeMicro() {
        return depositMicroList.size();
    }

    public static int GetSizeAuto() {
        return depositCarLoanList.size();
    }

    public static int GetSizeConsumer() {

        return depositConsumerList.size();
    }

    public static int GetSizeMortgage() {
        return depositMortgagList.size();
    }

    public static List<Integer> getDepositList(String session) {
        if(!session.equals(theId)) {
            depositCompareList = new ArrayList<>();
        }
        return depositCompareList;
    }

    public static List<Integer> getAGList() {
        return depositAgList;
    }

    public static List<Integer> getMortgageList(String session) {
        if(!session.equals(theId)) {
            depositMortgagList = new ArrayList<>();
        }
        return depositMortgagList;
    }

    public static List<Integer> getConsumerList(String session) {
        if(!session.equals(theId)) {
            depositConsumerList = new ArrayList<>();
        }
        return depositConsumerList;
    }

    public static List<Integer> getCarLoanList(String session) {
        if(!session.equals(theId)) {
            depositCarLoanList =new ArrayList<>();
        }
        return depositCarLoanList;
    }

    public static List<Integer> getMicroList(String session) {
        if(!session.equals(theId)) {
            depositMicroList = new ArrayList<>();
        }
        return depositMicroList;
    }

    public static List<Integer> getAgList(String session) {
        if(!session.equals(theId)){
            depositAgList = new ArrayList<>();
        }
        return depositAgList;
    }

    public static List<Integer> getCardList(String session) {
        if(!session.equals(theId)){
            depositCardsList= new ArrayList<>();
        }
        return depositCardsList;
    }

    public static void DeleteDepositList(String sessionId) throws SQLException {
        depositCompareList.removeAll(depositCompareList);
        DepositComDao depositComDao = new DepositComDao();
        depositComDao.DeletebySession(sessionId);
    }

    public static void DeleteMortgag(String sessionId) throws SQLException {
        depositMortgagList.removeAll(depositMortgagList);
        MortgageComDao mortgageComDao = new MortgageComDao();
        mortgageComDao.DeleteBySession(sessionId);
    }

    public static void DeleteCarLoan(String sessionId) throws SQLException {
        depositCarLoanList.removeAll(depositCarLoanList);
        CarComDao carComDao = new CarComDao();
        carComDao.DeleteBySession(sessionId);
    }

    public static void DeleteMicro(String sessionId)throws SQLException {
        depositMicroList.removeAll(depositMicroList);
        MicroComDao microComDao  = new MicroComDao();
        microComDao.DeleteBySession(sessionId);
    }

    public static void DeleteAg(String sessionId)throws SQLException {
        depositAgList.removeAll(depositAgList);
        AgComDao agComDao= new AgComDao();
        agComDao.DeleteBySession(sessionId);
    }

    public static void DeleteCard() {
        depositCardsList.removeAll(depositCardsList);

    }

    public static void DeleteConsumer(String sessionId)throws SQLException {
        depositConsumerList.removeAll(depositConsumerList);
        ConsumerComDao consumerComDao  = new ConsumerComDao();
        consumerComDao.DeletebySession(sessionId);
    }

    public static void DeleteComsumerById(int idRemove) {
        for (int i = 0; i < depositConsumerList.size(); i++) {
            if (depositConsumerList.get(i) == idRemove) {
                depositConsumerList.remove(i);
            }
        }

    }

    public static void DeleteDepositeById(int idRemove) {
        for (int i = 0; i < depositCompareList.size(); i++) {
            if (depositCompareList.get(i) == idRemove) {
                depositCompareList.remove(i);
            }
        }

    }

    public static void DeleteAutoById(int idRemove) {
        for (int i = 0; i < depositCarLoanList.size(); i++) {
            if (depositCarLoanList.get(i) == idRemove) {
                depositCarLoanList.remove(i);
            }
        }

    }

    public static void DeleteMicroById(int idRemove) {
        for (int i = 0; i < depositMicroList.size(); i++) {
            if (depositMicroList.get(i) == idRemove) {
                depositMicroList.remove(i);
            }
        }
    }

    public static void DeleteMortgageById(int idRemove) {
        for (int i = 0; i < depositMortgagList.size(); i++) {
            if (depositMortgagList.get(i) == idRemove) {
                depositMortgagList.remove(i);
            }
        }
    }

    public static void DeleteAGById(int idRemove) {
        for (int i = 0; i < depositAgList.size(); i++) {
            if (depositAgList.get(i) == idRemove) {
                depositAgList.remove(i);
            }
        }
    }


    public static int getMortgageListSize() {
        return depositMortgagList.size();
    }

    public static int getDepositListSize() {
        return depositCompareList.size();
    }

    public static int getConsumerListSize() {
        return depositConsumerList.size();
    }

    public static int getCarLoanListSize() {
        return depositCarLoanList.size();
    }

    public static int getMicroListSize() {
        return depositMicroList.size();
    }

    public static int getAgListSize() {
        return depositAgList.size();
    }

    public static int getCardListSize() {
        return depositCardsList.size();
    }


}
