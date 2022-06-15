package com.ithome.web.Bean;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CommentDaoComtroller;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Helpers.PojoHelper;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Deposit extends PojoHelper {

    private Long DId;
    private String productName;
    private int DMinAmount;
    private int DMaxAmount;
    private double DPercentage;
    private int DEquippingPossibilitiesid;
    private String DEquippingPossibilities;
    private int DEarlierWithdrawalAmountid;
    private String DEarlierWithdrawalAmount;
    private int DAutoExtendPeriodid;
    private String DAutoExtendPeriod;
    private int DCapitalizationPercentid;
    private String DCapitalizationPercent;
    private int lastLogic;
    private int timeLine;

    private double AmdMonth1;
    private double AmdMonth3;
    private double AmdMonth6;
    private double AmdMonth9;
    private double AmdMonth12;
    private double AmdMonth18;
    private double AmdMonth24;
    private double AmdMonth36;

    private double UsdMonth1;
    private double UsdMonth3;
    private double UsdMonth6;
    private double UsdMonth9;
    private double UsdMonth12;
    private double UsdMonth18;
    private double UsdMonth24;
    private double UsdMonth36;

    private double EurMonth1;
    private double EurMonth3;
    private double EurMonth6;
    private double EurMonth9;
    private double EurMonth12;
    private double EurMonth18;
    private double EurMonth24;
    private double EurMonth36;

    private double RubMonth1;
    private double RubMonth3;
    private double RubMonth6;
    private double RubMonth9;
    private double RubMonth12;
    private double RubMonth18;
    private double RubMonth24;
    private double RubMonth36;

    private String BankLink;
    private BanksDaoController banksDaoController = new BanksDaoController();



    public Deposit(int productCode, int bankId, String bankName, String bankWebSite, int minAge, int maxAge, int orderOfAppearance, int specialOffer,
                   int firstSearchList, int sendRequest, String currancy, int gotoPage, String productName, int DMinAmount, int DMaxAmount, double DPercentage,
                   int DEquippingPossibilitiesid, String DEquippingPossibilities, int DEarlierWithdrawalAmountid, String DEarlierWithdrawalAmount,
                   int DAutoExtendPeriodid, String DAutoExtendPeriod, int DCapitalizationPercentid, String DCapitalizationPercent, int timeLine) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, orderOfAppearance, specialOffer, firstSearchList, sendRequest, currancy, gotoPage);
        this.productName = productName;
        this.DMinAmount = DMinAmount;
        this.DMaxAmount = DMaxAmount;
        this.DPercentage = DPercentage;
        this.DEquippingPossibilitiesid = DEquippingPossibilitiesid;
        this.DEquippingPossibilities = DEquippingPossibilities;
        this.DEarlierWithdrawalAmountid = DEarlierWithdrawalAmountid;
        this.DEarlierWithdrawalAmount = DEarlierWithdrawalAmount;
        this.DAutoExtendPeriodid = DAutoExtendPeriodid;
        this.DAutoExtendPeriod = DAutoExtendPeriod;
        this.DCapitalizationPercentid = DCapitalizationPercentid;
        this.DCapitalizationPercent = DCapitalizationPercent;
        this.timeLine = timeLine;

    }

    public Deposit(int productCode, int bankId, String bankName, String bankWebSite, int minAge, int maxAge, String currancy, String productName, int DMinAmount,
                   int DMaxAmount, double DPercentage, int DEquippingPossibilitiesid, String DEquippingPossibilities, int DEarlierWithdrawalAmountid, String DEarlierWithdrawalAmount,
                   int DAutoExtendPeriodid, String DAutoExtendPeriod, int DCapitalizationPercentid, String DCapitalizationPercent) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);
        this.productName = productName;
        this.DMinAmount = DMinAmount;
        this.DMaxAmount = DMaxAmount;
        this.DPercentage = DPercentage;
        this.DEquippingPossibilitiesid = DEquippingPossibilitiesid;
        this.DEquippingPossibilities = DEquippingPossibilities;
        this.DEarlierWithdrawalAmountid = DEarlierWithdrawalAmountid;
        this.DEarlierWithdrawalAmount = DEarlierWithdrawalAmount;
        this.DAutoExtendPeriodid = DAutoExtendPeriodid;
        this.DAutoExtendPeriod = DAutoExtendPeriod;
        this.DCapitalizationPercentid = DCapitalizationPercentid;
        this.DCapitalizationPercent = DCapitalizationPercent;


    }

    public Deposit(int productCode, int bankId, String bankName, String bankWebSite, int minAge, int maxAge, String currancy, int timeline, String productName,
                   int dMinAmount, int dMaxAmount, double dPercentage, int dEquippingPossibilitiesid, String dEquippingPossibilities,
                   int dEarlierWithdrawalAmountid, String dEarlierWithdrawalAmount, int dAutoExtendPeriodid, String dAutoExtendPeriod, int dCapitalizationPercentid, String dCapitalizationPercent,String BankLink) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);
        this.productName = productName;
        this.DMinAmount = dMinAmount;
        this.DMaxAmount = dMaxAmount;
        this.DPercentage = dPercentage;
        this.timeLine = timeline;
        this.DEquippingPossibilitiesid = dEquippingPossibilitiesid;
        this.DEquippingPossibilities = dEquippingPossibilities;
        this.DEarlierWithdrawalAmountid = dEarlierWithdrawalAmountid;
        this.DEarlierWithdrawalAmount = dEarlierWithdrawalAmount;
        this.DAutoExtendPeriodid = dAutoExtendPeriodid;
        this.DAutoExtendPeriod = dAutoExtendPeriod;
        this.DCapitalizationPercentid = dCapitalizationPercentid;
        this.DCapitalizationPercent = dCapitalizationPercent;
        this.BankLink = BankLink;

    }

    public Deposit(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
    }

    public Deposit(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage, int lastLogic) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
        this.lastLogic = lastLogic;
    }

    public Deposit() {
    }

    public Deposit(String productName, String bankName, String bankWebSite, Long dId, int productCode,
                   int bankId, int dMinAmount, int dMaxAmount, int minAge, int maxAge, String currancy) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);
        this.productName = productName;
        this.DMinAmount = dMinAmount;
        this.DMaxAmount = dMaxAmount;

    }

    public Deposit(double amd1Month, double amd3Month, double amd6Month, double amd9Month, double amd12Month, double amd18Month, double amd24Month, double amd36Month) {
        this.AmdMonth1 = amd1Month;
        this.AmdMonth3 = amd3Month;
        this.AmdMonth6 = amd6Month;
        this.AmdMonth9 = amd9Month;
        this.AmdMonth12 = amd12Month;
        this.AmdMonth18 = amd18Month;
        this.AmdMonth24 = amd24Month;
        this.AmdMonth36 = amd36Month;
    }

    public Deposit(double amd1Month, double amd3Month, double amd6Month, double amd9Month, double amd12Month, double amd18Month, double amd24Month, double amd36Month, int i) {
        this.UsdMonth1 = amd1Month;
        this.UsdMonth3 = amd3Month;
        this.UsdMonth6 = amd6Month;
        this.UsdMonth9 = amd9Month;
        this.UsdMonth12 = amd12Month;
        this.UsdMonth18 = amd18Month;
        this.UsdMonth24 = amd24Month;
        this.UsdMonth36 = amd36Month;
    }

    public Deposit(double amd1Month, double amd3Month, double amd6Month, double amd9Month, double amd12Month, double amd18Month, double amd24Month, double amd36Month, double v) {
        this.RubMonth1 = amd1Month;
        this.RubMonth3 = amd3Month;
        this.RubMonth6 = amd6Month;
        this.RubMonth9 = amd9Month;
        this.RubMonth12 = amd12Month;
        this.RubMonth18 = amd18Month;
        this.RubMonth24 = amd24Month;
        this.RubMonth36 = amd36Month;
    }

    public Deposit(double amd1Month, double amd3Month, double amd6Month, double amd9Month, double amd12Month, double amd18Month, double amd24Month, double amd36Month, String a) {
        this.UsdMonth1 = amd1Month;
        this.UsdMonth3 = amd3Month;
        this.UsdMonth6 = amd6Month;
        this.UsdMonth9 = amd9Month;
        this.UsdMonth12 = amd12Month;
        this.UsdMonth18 = amd18Month;
        this.UsdMonth24 = amd24Month;
        this.UsdMonth36 = amd36Month;
    }

    public Deposit(double amd1Month, double amd3Month, double amd6Month, double amd9Month, double amd12Month, double amd18Month, double amd24Month, double amd36Month, boolean b) {
        this.EurMonth1 = amd1Month;
        this.EurMonth3 = amd3Month;
        this.EurMonth6 = amd6Month;
        this.EurMonth9 = amd9Month;
        this.EurMonth12 = amd12Month;
        this.EurMonth18 = amd18Month;
        this.EurMonth24 = amd24Month;
        this.EurMonth36 = amd36Month;
    }

    public double getUsdMonth1() {
        return UsdMonth1;
    }

    public void setUsdMonth1(double usdMonth1) {
        UsdMonth1 = usdMonth1;
    }

    public double getUsdMonth3() {
        return UsdMonth3;
    }

    public void setUsdMonth3(double usdMonth3) {
        UsdMonth3 = usdMonth3;
    }

    public double getUsdMonth6() {
        return UsdMonth6;
    }

    public void setUsdMonth6(double usdMonth6) {
        UsdMonth6 = usdMonth6;
    }

    public double getUsdMonth9() {
        return UsdMonth9;
    }

    public void setUsdMonth9(double usdMonth9) {
        UsdMonth9 = usdMonth9;
    }

    public double getUsdMonth12() {
        return UsdMonth12;
    }

    public void setUsdMonth12(double usdMonth12) {
        UsdMonth12 = usdMonth12;
    }

    public double getUsdMonth18() {
        return UsdMonth18;
    }

    public void setUsdMonth18(double usdMonth18) {
        UsdMonth18 = usdMonth18;
    }

    public double getUsdMonth24() {
        return UsdMonth24;
    }

    public void setUsdMonth24(double usdMonth24) {
        UsdMonth24 = usdMonth24;
    }

    public double getUsdMonth36() {
        return UsdMonth36;
    }

    public void setUsdMonth36(double usdMonth36) {
        UsdMonth36 = usdMonth36;
    }

    public double getEurMonth1() {
        return EurMonth1;
    }

    public void setEurMonth1(double eurMonth1) {
        EurMonth1 = eurMonth1;
    }

    public double getEurMonth3() {
        return EurMonth3;
    }

    public void setEurMonth3(double eurMonth3) {
        EurMonth3 = eurMonth3;
    }

    public double getEurMonth6() {
        return EurMonth6;
    }

    public void setEurMonth6(double eurMonth6) {
        EurMonth6 = eurMonth6;
    }

    public double getEurMonth9() {
        return EurMonth9;
    }

    public void setEurMonth9(double eurMonth9) {
        EurMonth9 = eurMonth9;
    }

    public double getEurMonth12() {
        return EurMonth12;
    }

    public void setEurMonth12(double eurMonth12) {
        EurMonth12 = eurMonth12;
    }

    public double getEurMonth18() {
        return EurMonth18;
    }

    public void setEurMonth18(double eurMonth18) {
        EurMonth18 = eurMonth18;
    }

    public double getEurMonth24() {
        return EurMonth24;
    }

    public void setEurMonth24(double eurMonth24) {
        EurMonth24 = eurMonth24;
    }

    public double getEurMonth36() {
        return EurMonth36;
    }

    public void setEurMonth36(double eurMonth36) {
        EurMonth36 = eurMonth36;
    }

    public double getRubMonth1() {
        return RubMonth1;
    }

    public void setRubMonth1(double rubMonth1) {
        RubMonth1 = rubMonth1;
    }

    public double getRubMonth3() {
        return RubMonth3;
    }

    public void setRubMonth3(double rubMonth3) {
        RubMonth3 = rubMonth3;
    }

    public double getRubMonth6() {
        return RubMonth6;
    }

    public void setRubMonth6(double rubMonth6) {
        RubMonth6 = rubMonth6;
    }

    public double getRubMonth9() {
        return RubMonth9;
    }

    public void setRubMonth9(double rubMonth9) {
        RubMonth9 = rubMonth9;
    }

    public double getRubMonth12() {
        return RubMonth12;
    }

    public void setRubMonth12(double rubMonth12) {
        RubMonth12 = rubMonth12;
    }

    public double getRubMonth18() {
        return RubMonth18;
    }

    public void setRubMonth18(double rubMonth18) {
        RubMonth18 = rubMonth18;
    }

    public double getRubMonth24() {
        return RubMonth24;
    }

    public void setRubMonth24(double rubMonth24) {
        RubMonth24 = rubMonth24;
    }

    public double getRubMonth36() {
        return RubMonth36;
    }

    public void setRubMonth36(double rubMonth36) {
        RubMonth36 = rubMonth36;
    }

    public String getBanksList() throws SQLException {
        return banksDaoController.getBankImageById(super.getBankId());

    }

    public String setBanksList(List<Deposit> depositList) throws SQLException {
        String bankImage = null;
        int bankid = 0;
        for (int i = 0; i < depositList.size(); i++) {
            bankid = depositList.get(i).getBankId();
            bankImage = banksDaoController.getBankBigImageById(bankid);
        }
        return bankImage;
    }

    public double getAmdMonth1() {
        return AmdMonth1;
    }

    public void setAmdMonth1(double amdMonth1) {
        AmdMonth1 = amdMonth1;
    }

    public double getAmdMonth3() {
        return AmdMonth3;
    }

    public void setAmdMonth3(double amdMonth3) {
        AmdMonth3 = amdMonth3;
    }

    public double getAmdMonth6() {
        return AmdMonth6;
    }

    public void setAmdMonth6(double amdMonth6) {
        AmdMonth6 = amdMonth6;
    }

    public double getAmdMonth9() {
        return AmdMonth9;
    }

    public void setAmdMonth9(double amdMonth9) {
        AmdMonth9 = amdMonth9;
    }

    public double getAmdMonth12() {
        return AmdMonth12;
    }

    public void setAmdMonth12(double amdMonth12) {
        AmdMonth12 = amdMonth12;
    }

    public double getAmdMonth18() {
        return AmdMonth18;
    }

    public void setAmdMonth18(double amdMonth18) {
        AmdMonth18 = amdMonth18;
    }

    public double getAmdMonth24() {
        return AmdMonth24;
    }

    public void setAmdMonth24(double amdMonth24) {
        AmdMonth24 = amdMonth24;
    }

    public double getAmdMonth36() {
        return AmdMonth36;
    }

    public void setAmdMonth36(double amdMonth36) {
        AmdMonth36 = amdMonth36;
    }

    public String getBankLink() {
        return BankLink;
    }

    public void setBankLink(String bankLink) {
        BankLink = bankLink;
    }

    public int getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(int timeLine) {
        this.timeLine = timeLine;
    }

    public int getLastLogic() {
        return lastLogic;
    }

    public void setLastLogic(int lastLogic) {
        this.lastLogic = lastLogic;
    }

    public Long getDId() {
        return DId;
    }

    public void setDId(Long DId) {
        this.DId = DId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDMinAmount() {
        return DMinAmount;
    }

    public void setDMinAmount(int DMinAmount) {
        this.DMinAmount = DMinAmount;
    }

    public int getDMaxAmount() {
        return DMaxAmount;
    }

    public void setDMaxAmount(int DMaxAmount) {
        this.DMaxAmount = DMaxAmount;
    }

    public double getDPercentage() {
        return DPercentage;
    }

    public void setDPercentage(double DPercentage) {
        this.DPercentage = DPercentage;
    }

    public int getDEquippingPossibilitiesid() {
        return DEquippingPossibilitiesid;
    }

    public void setDEquippingPossibilitiesid(int DEquippingPossibilitiesid) {
        this.DEquippingPossibilitiesid = DEquippingPossibilitiesid;
    }

    public String getDEquippingPossibilities() {
        return DEquippingPossibilities;
    }

    public void setDEquippingPossibilities(String DEquippingPossibilities) {
        this.DEquippingPossibilities = DEquippingPossibilities;
    }

    public int getDEarlierWithdrawalAmountid() {
        return DEarlierWithdrawalAmountid;
    }

    public void setDEarlierWithdrawalAmountid(int DEarlierWithdrawalAmountid) {
        this.DEarlierWithdrawalAmountid = DEarlierWithdrawalAmountid;
    }

    public String getDEarlierWithdrawalAmount() {
        return DEarlierWithdrawalAmount;
    }

    public void setDEarlierWithdrawalAmount(String DEarlierWithdrawalAmount) {
        this.DEarlierWithdrawalAmount = DEarlierWithdrawalAmount;
    }

    public int getDAutoExtendPeriodid() {
        return DAutoExtendPeriodid;
    }

    public void setDAutoExtendPeriodid(int DAutoExtendPeriodid) {
        this.DAutoExtendPeriodid = DAutoExtendPeriodid;
    }

    public String getDAutoExtendPeriod() {
        return DAutoExtendPeriod;
    }

    public void setDAutoExtendPeriod(String DAutoExtendPeriod) {
        this.DAutoExtendPeriod = DAutoExtendPeriod;
    }

    public int getDCapitalizationPercentid() {
        return DCapitalizationPercentid;
    }

    public void setDCapitalizationPercentid(int DCapitalizationPercentid) {
        this.DCapitalizationPercentid = DCapitalizationPercentid;
    }

    public String getDCapitalizationPercent() {
        return DCapitalizationPercent;
    }

    public void setDCapitalizationPercent(String DCapitalizationPercent) {
        this.DCapitalizationPercent = DCapitalizationPercent;
    }



}