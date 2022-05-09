package com.ithome.web.Helpers;

import com.ithome.web.Bean.*;


import java.util.ArrayList;
import java.util.List;

public class SearchDeep extends StringIntegerChecker  {

    private List<Mortgage> searchMortgageList = new ArrayList<>();
    private List<MicroLoans> searchMicroLoansList = new ArrayList<>();
    private List<Deposit> searchDepositList = new ArrayList<>();
    private List<ConsumerCredit> searchConsumerCreditList = new ArrayList<>();
    private List<CarLoans> searchListCarLoans = new ArrayList<>();
    private List<Cards> searchListCards = new ArrayList<>();
    private List<Banks> searchListBanks = new ArrayList<>();
    private List<AgriculturalCredit> searchListAg = new ArrayList<>();

    public List<Mortgage> searchMortgages(List<Mortgage> mortgage, String search) {
        return Smortgages(mortgage, search);
    }

    private List<Mortgage> Smortgages(List<Mortgage> mortgage, String search) {
        String searchNumber = returnString(search);
        Mortgage mortgageO = null;
        for (int i = 0; i < mortgage.size(); i++) {
            if (/*mortgage.get(i).getProductName().equals(search) ||*/
                    mortgage.get(i).getBankName().equals(search) ||
                    mortgage.get(i).getBankWebSite().equals(search) ||
                    mortgage.get(i).getMId() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getBankId() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getProductCode() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMinAmount() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMaxAmount() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMinAge() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMaxAge() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMinLoan() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMaxLoan() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMinDownPayment() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMaxDownPayment() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMinPeriodMonth() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMaxPeriodMonth() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMinServiceFee() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getMMaxServiceFee() == Integer.parseInt(searchNumber) ||
                    mortgage.get(i).getCurrancy().equals(search)) {
                mortgageO = new Mortgage(mortgage.get(i).getProductName(),
                        mortgage.get(i).getBankName(),
                        mortgage.get(i).getBankWebSite(),
                        mortgage.get(i).getMId(),
                        mortgage.get(i).getBankId(),
                        mortgage.get(i).getProductCode(),
                        mortgage.get(i).getMMinAmount(),
                        mortgage.get(i).getMMaxAmount(),
                        mortgage.get(i).getMinAge(),
                        mortgage.get(i).getMaxAge(),
                        mortgage.get(i).getMMinLoan(),
                        mortgage.get(i).getMMaxLoan(),
                        mortgage.get(i).getMMinDownPayment(),
                        mortgage.get(i).getMMaxDownPayment(),
                        mortgage.get(i).getMMinPeriodMonth(),
                        mortgage.get(i).getMMaxPeriodMonth(),
                        mortgage.get(i).getMMinServiceFee(),
                        mortgage.get(i).getMMaxServiceFee(),
                        mortgage.get(i).getCurrancy());
                searchMortgageList.add(mortgageO);
            }
        }
        return searchMortgageList;
    }

    public List<MicroLoans> searchMicroLoan(List<MicroLoans> micro, String search) {
        return SMicro(micro, search);
    }

    private List<MicroLoans> SMicro(List<MicroLoans> micro, String search) {
        String searchNumber = returnString(search);
        MicroLoans microLoans = null;
        for (int i = 0; i < micro.size(); i++) {
            if (micro.get(i).getProductName().equals(search) ||
                    micro.get(i).getBankName().equals(search) ||
                    micro.get(i).getBankWebSite().equals(search) ||
                    micro.get(i).getMLId() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getBankId() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getProductCode() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMLMinAmount() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMLMaxAmount() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMinAge() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMaxAge() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMLMinLoan() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMLMaxAmount() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMMinPeriodDays() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMMaxPeriodDays() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMMinServiceFee() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getMMaxServiceFee() == Integer.parseInt(searchNumber) ||
                    micro.get(i).getCurrancy().equals(search)) {
                microLoans = new MicroLoans(
                        micro.get(i).getProductName(),
                        micro.get(i).getBankName(),
                        micro.get(i).getBankWebSite(),
                        micro.get(i).getMLId(),
                        micro.get(i).getBankId(),
                        micro.get(i).getProductCode(),
                        micro.get(i).getMLMinAmount(),
                        micro.get(i).getMLMaxAmount(),
                        micro.get(i).getMinAge(),
                        micro.get(i).getMaxAge(),
                        micro.get(i).getMLMinLoan(),
                        micro.get(i).getMLMaxAmount(),
                        micro.get(i).getMMinPeriodDays(),
                        micro.get(i).getMMaxPeriodDays(),
                        micro.get(i).getMMinServiceFee(),
                        micro.get(i).getMMaxServiceFee(),
                        micro.get(i).getCurrancy());
                searchMicroLoansList.add(microLoans);
            }

        }
        return searchMicroLoansList;
    }

    public List<Deposit> searchD(List<Deposit> deposiList, String search) {
        return sDeposite(deposiList, search);
    }

    private List<Deposit> sDeposite(List<Deposit> deposiList, String search) {
        String searchNumber = returnString(search);
        Deposit deposit = null;
        for (int i = 0; i < deposiList.size(); i++) {
            if (deposiList.get(i).getProductName().equals(search) ||
                    deposiList.get(i).getBankName().equals(search) ||
                    deposiList.get(i).getBankWebSite().equals(search) ||
                    deposiList.get(i).getDId() == Integer.parseInt(searchNumber) ||
                    deposiList.get(i).getProductCode() == Integer.parseInt(searchNumber) ||
                    deposiList.get(i).getBankId() == Integer.parseInt(searchNumber) ||
                    deposiList.get(i).getDMinAmount() == Integer.parseInt(searchNumber) ||
                    deposiList.get(i).getDMaxAmount() == Integer.parseInt(searchNumber) ||
                    deposiList.get(i).getMinAge() == Integer.parseInt(searchNumber) ||
                    deposiList.get(i).getMaxAge() == Integer.parseInt(searchNumber) ||
                    deposiList.get(i).getCurrancy().equals(search)) {
                deposit = new Deposit(deposiList.get(i).getProductName(),
                        deposiList.get(i).getBankName(),
                        deposiList.get(i).getBankWebSite(),
                        deposiList.get(i).getDId(),
                        deposiList.get(i).getProductCode(),
                        deposiList.get(i).getBankId(),
                        deposiList.get(i).getDMinAmount(),
                        deposiList.get(i).getDMaxAmount(),
                        deposiList.get(i).getMinAge(),
                        deposiList.get(i).getMaxAge(),
                        deposiList.get(i).getCurrancy());
                searchDepositList.add(deposit);
            }
        }
        return searchDepositList;
    }

    public List<ConsumerCredit> searchCCResult(List<ConsumerCredit> ccList, String search) {
        return SCC(ccList, search);
    }

    private List<ConsumerCredit> SCC(List<ConsumerCredit> ccList, String search) {
        String searchNumber = returnString(search);
        ConsumerCredit consumerCredit = null;
        for (int i = 0; i < ccList.size(); i++) {
            if (ccList.get(i).getProductName().equals(search) ||
                    ccList.get(i).getBankName().equals(search) ||
                    /*ccList.get(i).getBankWebSite().equals(searchNumber) ||*/
                    ccList.get(i).getCLId() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getBankId() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getProductCode() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getMinAge() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getMaxAge() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMinAmount() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMaxAmount() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMinServiceFee() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMaxServiceFee() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMinLoan() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMaxLoan() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMinPeriodMonth() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCCMaxPeriodMonth() == Integer.parseInt(searchNumber) ||
                    ccList.get(i).getCurrancy().equals(search)) {
                consumerCredit = new ConsumerCredit(
                        ccList.get(i).getProductName(),
                        ccList.get(i).getBankName(),
                        ccList.get(i).getBankWebSite(),
                        ccList.get(i).getCLId(),
                        ccList.get(i).getBankId(),
                        ccList.get(i).getProductCode(),
                        ccList.get(i).getMinAge(),
                        ccList.get(i).getMaxAge(),
                        ccList.get(i).getCCMinAmount(),
                        ccList.get(i).getCCMaxAmount(),
                        ccList.get(i).getCCMinServiceFee(),
                        ccList.get(i).getCCMaxServiceFee(),
                        ccList.get(i).getCCMinLoan(),
                        ccList.get(i).getCCMaxLoan(),
                        ccList.get(i).getCCMinPeriodMonth(),
                        ccList.get(i).getCCMaxPeriodMonth(),
                        ccList.get(i).getCurrancy());
                searchConsumerCreditList.add(consumerCredit);
            }
        }
        return searchConsumerCreditList;

    }

    public List<CarLoans> searchCarLoanResult(List<CarLoans> carLoanList, String search) {
        return SCarLoan(carLoanList, search);
    }

    private List<CarLoans> SCarLoan(List<CarLoans> carLoanList, String search) {
        String searchNumber = returnString(search);
        CarLoans carLoans = null;
        for (int i = 0; i < carLoanList.size(); i++) {
            if (carLoanList.get(i).getProductName().equals(search) ||
                    carLoanList.get(i).getBankName().equals(search) ||
                    carLoanList.get(i).getBankWebSite().equals(search) ||
                    carLoanList.get(i).getCLId() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getProductCode() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getBankId() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getMinAge() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getMaxAge() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMinAmount() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMaxAmount() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMinLoan() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMaxLoan() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMinDownPayment() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMaxDownPayment() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMinPeriodMonths() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMaxPeriodMonths() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMinServiceFee() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCLMaxServiceFee() == Integer.parseInt(searchNumber) ||
                    carLoanList.get(i).getCurrancy().equals(search)) {
                carLoans = new CarLoans(
                        carLoanList.get(i).getProductName(),
                        carLoanList.get(i).getBankName(),
                        carLoanList.get(i).getBankWebSite(),
                        carLoanList.get(i).getCLId(),
                        carLoanList.get(i).getProductCode(),
                        carLoanList.get(i).getBankId(),
                        carLoanList.get(i).getMinAge(),
                        carLoanList.get(i).getMaxAge(),
                        carLoanList.get(i).getCLMinAmount(),
                        carLoanList.get(i).getCLMaxAmount(),
                        carLoanList.get(i).getCLMinLoan(),
                        carLoanList.get(i).getCLMaxLoan(),
                        carLoanList.get(i).getCLMinDownPayment(),
                        carLoanList.get(i).getCLMaxDownPayment(),
                        carLoanList.get(i).getCLMinPeriodMonths(),
                        carLoanList.get(i).getCLMaxPeriodMonths(),
                        carLoanList.get(i).getCLMinServiceFee(),
                        carLoanList.get(i).getCLMaxServiceFee(),
                        carLoanList.get(i).getCurrancy());
                searchListCarLoans.add(carLoans);
            }
        }
        return searchListCarLoans;
    }

    public List<Cards> searchCardResult(List<Cards> cardList, String search) throws NumberFormatException {
        return SCard(cardList, search);
    }

    private List<Cards> SCard(List<Cards> cardList, String search) {
        String searchNumber = returnString(search);
        Cards cards = null;
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardName().equals(search) ||
                    cardList.get(i).getCardType().equals(search) ||
                    cardList.get(i).getCardMinCreditLimit() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCardMaxCreditLimit() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCardMinServiceFee() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCardMaxServiceFee() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCardPerMinCashBack() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCardPerMaxCashBack() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCardPerMinDiscount() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCardPerMaxDiscount() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getBankName().equals(search) ||
                    cardList.get(i).getBankWebSite().equals(search) ||
                    cardList.get(i).getMinAge() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getMaxAge() == Integer.parseInt(searchNumber) ||
                    cardList.get(i).getCurrancy().equals(search)) {


                cards = new Cards(
                        cardList.get(i).getCardId(),
                        cardList.get(i).getBankName(),
                        cardList.get(i).getBankId(),
                        cardList.get(i).getBankWebSite(),
                        cardList.get(i).getProductCode(),
                        cardList.get(i).getCardName(),
                        cardList.get(i).getCardType(),
                        cardList.get(i).getCardMinCreditLimit(),
                        cardList.get(i).getCardMaxCreditLimit(),
                        cardList.get(i).getCardMinServiceFee(),
                        cardList.get(i).getCardMaxServiceFee(),
                        cardList.get(i).getCardPerMinCashBack(),
                        cardList.get(i).getCardPerMaxCashBack(),
                        cardList.get(i).getCardPerMinDiscount(),
                        cardList.get(i).getCardPerMaxDiscount(),
                        cardList.get(i).getMinAge(),
                        cardList.get(i).getMaxAge(),
                        cardList.get(i).getCurrancy());
                searchListCards.add(cards);
            }
        }
        return searchListCards;
    }

    public List<Banks> searchBankResult(List<Banks> bankList, String search) {
        return SBankresult(bankList, search);
    }

    private List<Banks> SBankresult(List<Banks> bankList, String search) {
        String searchNumber = returnString(search);
        Banks banks = null;
        for (
                int i = 0; i < bankList.size(); i++) {
            if (bankList.get(i).getBankName().equals(search) ||
                    bankList.get(i).getBankWebSite().equals(search) ||
                    bankList.get(i).getBankAddress().equals(search) ||
                    bankList.get(i).getBankEmailAddress().equals(search) ||
                    bankList.get(i).getBankfaxNumber().equals(search) ||
                    bankList.get(i).getBankHQ().equals(search) ||
                    bankList.get(i).getBankPhoneNumber().equals(search) ||
                    bankList.get(i).getBankSwiftCode().equals(search)) {
                banks = new Banks(bankList.get(i).getBankId(), bankList.get(i).getBankName(), bankList.get(i).getBankWebSite(), bankList.get(i).getBankAddress(), bankList.get(i).getBankEmailAddress(), bankList.get(i).getBankfaxNumber()
                        , bankList.get(i).getBankHQ(), bankList.get(i).getBankPhoneNumber(), bankList.get(i).getBankSwiftCode());
                searchListBanks.add(banks);
            }
        }
        return searchListBanks;
    }

    public List<AgriculturalCredit> searchAgResult(List<AgriculturalCredit> agList, String search) {
        return SAg(agList, search);
    }

    private List<AgriculturalCredit> SAg(List<AgriculturalCredit> agList, String search) {
        String searchNumber = returnString(search);
        AgriculturalCredit ag = null;
        for (int i = 0; i < agList.size(); i++) {
            if (agList.get(i).getProductName().equals(search) ||
                    agList.get(i).getBankName().equals(search) ||
                    agList.get(i).getProductCode() == Integer.parseInt(searchNumber) ||
                    agList.get(i).getBankWebSite().equals(search) ||
                    agList.get(i).getACMaxPeriodMonth() == Integer.parseInt(searchNumber) ||
                    agList.get(i).getACMinPeriodMonth() == Integer.parseInt(searchNumber) ||
                    agList.get(i).getACMaxAmount() == Integer.parseInt(searchNumber) ||
                    agList.get(i).getACMinAmount() == Integer.parseInt(searchNumber) ||
                    agList.get(i).getMinAge() == Integer.parseInt(searchNumber) ||
                    agList.get(i).getMaxAge() == Integer.parseInt(searchNumber)) {
                ag = new AgriculturalCredit(
                        agList.get(i).getACId(),
                        agList.get(i).getBankName(),
                        agList.get(i).getBankId(),
                        agList.get(i).getBankWebSite(),
                        agList.get(i).getProductName(),
                        agList.get(i).getProductCode(),
                        agList.get(i).getACMaxPeriodMonth(),
                        agList.get(i).getACMinPeriodMonth(),
                        agList.get(i).getACMaxAmount(),
                        agList.get(i).getACMinAmount(),
                        agList.get(i).getMinAge(),
                        agList.get(i).getMaxAge(),
                        agList.get(i).getACFactual(),
                        agList.get(i).getACMinServiceFee(),
                        agList.get(i).getACMaxServiceFee(),
                        agList.get(i).getACMinLoan(),
                        agList.get(i).getACMaxLoan(),
                        agList.get(i).getCurrancy());
                searchListAg.add(ag);
            }

        }
        return searchListAg;
    }
}
