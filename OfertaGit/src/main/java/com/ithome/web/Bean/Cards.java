package com.ithome.web.Bean;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Helpers.PojoHelper;

import java.sql.SQLException;
import java.util.List;

public class Cards extends PojoHelper {
    private Long cardId;
    private String cardName;
    private long cardTypeId;
    private String cardType;
    private String cardImage;
    private String PDF;
    private String PDFSale;
    private int cardMinServiceFee;
    private int cardMaxServiceFee;
    private double cardPerMinCashBack;
    private double cardPerMaxCashBack;
    private double cardPerMinDiscount;
    private double cardPerMaxDiscount;
    private int cardMinCreditLimit;
    private int cardMaxCreditLimit;
    private double cardPerCreditLimit;
    private double cardPerFactual;
    private int cardGracePeriod;
    private double cardPerOnCreditStanding;
    private int lastLogic;
    private double minCashBack;
    private double maxCashBack;
    private String Details;
    private String bankLink;
    private String Cashback;
    private String Timer;
    private String Free;
    private String Debit;
    private String Crediting;
    private String CardInfo;
    private String lastLink;


    private List<Cards> cardsList;
    private BanksDaoController banksDaoController = new BanksDaoController();

    public Cards(int productCode, int bankId, String bankName, String bankWebSite, int minAge, int maxAge, int orderOfAppearance, int specialOffer, int firstSearchList,
                 int sendRequest, String currancy, int gotoPage, Long cardId, String cardName, long cardTypeId, String cardType, String cardImage,
                 int cardMinServiceFee, int cardMaxServiceFee, double cardPerMinCashBack, double cardPerMaxCashBack, double cardPerMinDiscount,
                 double cardPerMaxDiscount, int cardMinCreditLimit, int cardMaxCreditLimit, double cardPerCreditLimit, double cardPerFactual,
                 int cardGracePeriod, double cardPerOnCreditStanding,int lastlogic, List<Cards> cardsList) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, orderOfAppearance, specialOffer, firstSearchList, sendRequest, currancy, gotoPage);
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardTypeId = cardTypeId;
        this.cardType = cardType;
        this.cardImage = cardImage;
        this.cardMinServiceFee = cardMinServiceFee;
        this.cardMaxServiceFee = cardMaxServiceFee;
        this.cardPerMinCashBack = cardPerMinCashBack;
        this.cardPerMaxCashBack = cardPerMaxCashBack;
        this.cardPerMinDiscount = cardPerMinDiscount;
        this.cardPerMaxDiscount = cardPerMaxDiscount;
        this.cardMinCreditLimit = cardMinCreditLimit;
        this.cardMaxCreditLimit = cardMaxCreditLimit;
        this.cardPerCreditLimit = cardPerCreditLimit;
        this.cardPerFactual = cardPerFactual;
        this.cardGracePeriod = cardGracePeriod;
        this.cardPerOnCreditStanding = cardPerOnCreditStanding;
        this.lastLogic=lastlogic;
        this.cardsList = cardsList;
    }

    public Cards(Long cardId, String cardName, long cardTypeId, String cardType, String cardImage,
                 int cardMinServiceFee, int cardMaxServiceFee, double cardPerMinCashBack,
                 double cardPerMaxCashBack, double cardPerMinDiscount, double cardPerMaxDiscount,
                 int cardMinCreditLimit, int cardMaxCreditLimit, double cardPerCreditLimit, double cardPerFactual, int cardGracePeriod, double cardPerOnCreditStanding, List<Cards> cardsList) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardTypeId = cardTypeId;
        this.cardType = cardType;
        this.cardImage = cardImage;
        this.cardMinServiceFee = cardMinServiceFee;
        this.cardMaxServiceFee = cardMaxServiceFee;
        this.cardPerMinCashBack = cardPerMinCashBack;
        this.cardPerMaxCashBack = cardPerMaxCashBack;
        this.cardPerMinDiscount = cardPerMinDiscount;
        this.cardPerMaxDiscount = cardPerMaxDiscount;
        this.cardMinCreditLimit = cardMinCreditLimit;
        this.cardMaxCreditLimit = cardMaxCreditLimit;
        this.cardPerCreditLimit = cardPerCreditLimit;
        this.cardPerFactual = cardPerFactual;
        this.cardGracePeriod = cardGracePeriod;
        this.cardPerOnCreditStanding = cardPerOnCreditStanding;
        this.cardsList = cardsList;
    }

    public Cards() {
    }


    public Cards(String cardImage) {
        this.cardImage = cardImage;
    }

    public Cards(int productCode, String cardName, int bankId, String bankName,
                 String bankWebSite, int cardTypeId, String cardType, String imageAddress,
                 int cardMinServiceFee, int cardMaxServiceFee, double cardPerMinCashBack,
                 double cardPerMaxCashBack, double cardPerMaxDiscount, double cardPerMinDiscount,
                 int cardMinCreditLimit, int cardMaxCreditLimit, double cardPerCreditLimit, double cardPerFactual,
                 int cardGracePeriod, String currancy, int minAge, int maxAge, double cardPerOnCreditStanding, double minCashBack, double maxCashBack, String bankLink, String details, String cashback, String timer, String free, String debit, String crediting, String cardInfo,String lastLink) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);

        this.cardName = cardName;
        this.cardTypeId = cardTypeId;
        this.cardType = cardType;
        this.cardImage = imageAddress;
        this.cardMinServiceFee = cardMinServiceFee;
        this.cardMaxServiceFee = cardMaxServiceFee;
        this.cardPerMinCashBack = cardPerMinCashBack;
        this.cardPerMaxCashBack = cardPerMaxCashBack;
        this.cardPerMinDiscount = cardPerMinDiscount;
        this.cardPerMaxDiscount = cardPerMaxDiscount;
        this.cardMinCreditLimit = cardMinCreditLimit;
        this.cardMaxCreditLimit = cardMaxCreditLimit;
        this.cardPerCreditLimit = cardPerCreditLimit;
        this.cardPerFactual = cardPerFactual;
        this.cardGracePeriod = cardGracePeriod;
        this.cardPerOnCreditStanding = cardPerOnCreditStanding;
        this.minCashBack=minCashBack;
        this.maxCashBack=maxCashBack;
        this.bankLink =bankLink;
        this.Details=details;
        this.Cashback= cashback;
        this.Timer=timer;
        this.Free = free;
        this.Debit  = debit;
        this.Crediting = crediting;
        this.CardInfo=cardInfo;
        this.lastLink=lastLink;

    }

    public Cards(int productCode, String cardName, int bankId, String bankName, String bankWebSite, int cardTypeId, String cardType, int cardMinServiceFee,
                 int cardMaxServiceFee, double cardPerMinCashBack, double cardPerMaxCashBack, double cardPerMaxDiscount,
                 double cardPerMinDiscount, int cardMinCreditLimit, int cardMaxCreditLimit, double cardPerCreditLimit,
                 double cardPerFactual, int cardGracePeriod, String currancy, int minAge, int maxAge, double cardPerOnCreditStanding, double minCashBack, double maxCashBack, String bankLink, String details, String cashback, String timer, String free, String debit, String crediting, String cardInfo,String LastLink) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);

        this.cardName = cardName;
        this.cardTypeId = cardTypeId;
        this.cardType = cardType;
        this.cardMinServiceFee = cardMinServiceFee;
        this.cardMaxServiceFee = cardMaxServiceFee;
        this.cardPerMinCashBack = cardPerMinCashBack;
        this.cardPerMaxCashBack = cardPerMaxCashBack;
        this.cardPerMinDiscount = cardPerMinDiscount;
        this.cardPerMaxDiscount = cardPerMaxDiscount;
        this.cardMinCreditLimit = cardMinCreditLimit;
        this.cardMaxCreditLimit = cardMaxCreditLimit;
        this.cardPerCreditLimit = cardPerCreditLimit;
        this.cardPerFactual = cardPerFactual;
        this.cardGracePeriod = cardGracePeriod;
        this.cardPerOnCreditStanding = cardPerOnCreditStanding;
        this.minCashBack=minCashBack;
        this.maxCashBack=maxCashBack;
        this.bankLink =bankLink;
        this.Details=details;
        this.Cashback= cashback;
        this.Timer=timer;
        this.Free = free;
        this.Debit  = debit;
        this.Crediting = crediting;
        this.CardInfo=cardInfo;
        this.lastLink=LastLink;
    }


    public Cards(Long cardId, String bankName, int bankId, String bankWebSite, int productCode, String cardName,
                 String cardType, int cardMinCreditLimit, int cardMaxCreditLimit, int cardMinServiceFee, int cardMaxServiceFee, double cardPerMinCashBack,
                 double cardPerMaxCashBack, double cardPerMinDiscount, double cardPerMaxDiscount, int minAge, int maxAge, String currancy) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);
        this.cardName = cardName;
        this.cardId = cardId;
        this.cardType = cardType;
        this.cardMinServiceFee = cardMinServiceFee;
        this.cardMaxServiceFee = cardMaxServiceFee;
        this.cardPerMinCashBack = cardPerMinCashBack;
        this.cardPerMaxCashBack = cardPerMaxCashBack;
        this.cardPerMinDiscount = cardPerMinDiscount;
        this.cardPerMaxDiscount = cardPerMaxDiscount;
        this.cardMinCreditLimit = cardMinCreditLimit;
        this.cardMaxCreditLimit = cardMaxCreditLimit;

    }

    public String getLastLink() {
        return lastLink;
    }

    public void setLastLink(String lastLink) {
        this.lastLink = lastLink;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public String getPDFSale() {
        return PDFSale;
    }

    public void setPDFSale(String PDFSale) {
        this.PDFSale = PDFSale;
    }

    public String getCardInfo() {
        return CardInfo;
    }

    public void setCardInfo(String cardInfo) {
        CardInfo = cardInfo;
    }

    public String getBanksList() throws SQLException {
        return banksDaoController.getBankImageById(super.getBankId());

    }

    public String setBanksList(List<Cards> depositList) throws SQLException {
        String bankImage = null;
        int bankid = 0;
        for (int i = 0; i < depositList.size(); i++) {
            bankid = depositList.get(i).getBankId();
            bankImage = banksDaoController.getBankImageById(bankid);
        }
        return bankImage;
    }

    public String getCashback() {
        return Cashback;
    }

    public void setCashback(String cashback) {
        Cashback = cashback;
    }

    public String getTimer() {
        return Timer;
    }

    public void setTimer(String timer) {
        Timer = timer;
    }

    public String getFree() {
        return Free;
    }

    public void setFree(String free) {
        Free = free;
    }

    public String getDebit() {
        return Debit;
    }

    public void setDebit(String debit) {
        Debit = debit;
    }

    public String getCrediting() {
        return Crediting;
    }

    public void setCrediting(String crediting) {
        Crediting = crediting;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getBankLink() {
        return bankLink;
    }

    public void setBankLink(String bankLink) {
        this.bankLink = bankLink;
    }

    public int getLastLogic() {
        return lastLogic;
    }

    public void setLastLogic(int lastLogic) {
        this.lastLogic = lastLogic;
    }

    public double getMinCashBack() {
        return minCashBack;
    }

    public void setMinCashBack(double minCashBack) {
        this.minCashBack = minCashBack;
    }

    public double getMaxCashBack() {
        return maxCashBack;
    }

    public void setMaxCashBack(double maxCashBack) {
        this.maxCashBack = maxCashBack;
    }

    public int getLastlogic() {
        return lastLogic;
    }

    public void setLastlogic(int lastlogic) {
        this.lastLogic = lastlogic;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public long getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public int getCardMinServiceFee() {
        return cardMinServiceFee;
    }

    public void setCardMinServiceFee(int cardMinServiceFee) {
        this.cardMinServiceFee = cardMinServiceFee;
    }

    public int getCardMaxServiceFee() {
        return cardMaxServiceFee;
    }

    public void setCardMaxServiceFee(int cardMaxServiceFee) {
        this.cardMaxServiceFee = cardMaxServiceFee;
    }

    public double getCardPerMinCashBack() {
        return cardPerMinCashBack;
    }

    public void setCardPerMinCashBack(double cardPerMinCashBack) {
        this.cardPerMinCashBack = cardPerMinCashBack;
    }

    public double getCardPerMaxCashBack() {
        return cardPerMaxCashBack;
    }

    public void setCardPerMaxCashBack(double cardPerMaxCashBack) {
        this.cardPerMaxCashBack = cardPerMaxCashBack;
    }

    public double getCardPerMinDiscount() {
        return cardPerMinDiscount;
    }

    public void setCardPerMinDiscount(double cardPerMinDiscount) {
        this.cardPerMinDiscount = cardPerMinDiscount;
    }

    public double getCardPerMaxDiscount() {
        return cardPerMaxDiscount;
    }

    public void setCardPerMaxDiscount(double cardPerMaxDiscount) {
        this.cardPerMaxDiscount = cardPerMaxDiscount;
    }

    public int getCardMinCreditLimit() {
        return cardMinCreditLimit;
    }

    public void setCardMinCreditLimit(int cardMinCreditLimit) {
        this.cardMinCreditLimit = cardMinCreditLimit;
    }

    public int getCardMaxCreditLimit() {
        return cardMaxCreditLimit;
    }

    public void setCardMaxCreditLimit(int cardMaxCreditLimit) {
        this.cardMaxCreditLimit = cardMaxCreditLimit;
    }

    public double getCardPerCreditLimit() {
        return cardPerCreditLimit;
    }

    public void setCardPerCreditLimit(double cardPerCreditLimit) {
        this.cardPerCreditLimit = cardPerCreditLimit;
    }

    public double getCardPerFactual() {
        return cardPerFactual;
    }

    public void setCardPerFactual(double cardPerFactual) {
        this.cardPerFactual = cardPerFactual;
    }

    public int getCardGracePeriod() {
        return cardGracePeriod;
    }

    public void setCardGracePeriod(int cardGracePeriod) {
        this.cardGracePeriod = cardGracePeriod;
    }

    public double getCardPerOnCreditStanding() {
        return cardPerOnCreditStanding;
    }

    public void setCardPerOnCreditStanding(double cardPerOnCreditStanding) {
        this.cardPerOnCreditStanding = cardPerOnCreditStanding;
    }

    public List<Cards> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<Cards> cardsList) {
        this.cardsList = cardsList;
    }
}