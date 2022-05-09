package com.ithome.web;

import java.util.ArrayList;
import java.util.List;

public class MortgageCar {
    private  long inputMoney;
    private  long monthOfCredit;
    private  float percentOfAmount;

    List<Repayment> repayments = new ArrayList<>();
    List<Repayment> repaymentList = new ArrayList<>();
    List<Repayment> repaymentListfinal = new ArrayList<>();

    public List<Repayment> startMortgage(float percentage, Long Amount,float Months){
        repaymentList = new ArrayList<>();
        inputMoney=Amount;
        monthOfCredit = (long) (Months );
        percentOfAmount=percentage;
        calculateRepayment1(percentOfAmount,inputMoney,monthOfCredit);
        repaymentList =  printRepayments();
        return repaymentList;
    }
    private  List<Repayment> printRepayments(){
        repaymentListfinal = new ArrayList<>();
        for (int i = 0; i < repayments.size(); i++) {
            System.out.println(repayments.get(i).getCreditBalance()  +" "+repayments.get(i).getPercentageOfAmount() +  " " +  repayments.get(i).getLoanRepayment()+ " "  +repayments.get(i).getTotalPayment());
            repaymentList.add(new Repayment(repayments.get(i).getCreditBalance(),repayments.get(i).getPercentageOfAmount(),repayments.get(i).getLoanRepayment(),repayments.get(i).getTotalPayment()));
        }
        return repaymentList;
    }
    private float getPercent(float inputNumber, float percent){
        return ((inputNumber * percent)/100);
    }


    private  void calculateRepayment1(float percentage, Long amount, Long months){
        percentOfAmount = getPercent(inputMoney,percentage);
        float creditBalance = 0;
        float percentageOfAmount = repaymentPercentageOfAmount();
        float loanRepayment = repaymentLoanRepayment(inputMoney);
        float totalPayment = repaymentTotalPayment(inputMoney);
        repayments.add(new Repayment(inputMoney, (long) percentageOfAmount, (long) loanRepayment, (int) totalPayment));
        for (int i = 0; i < monthOfCredit-1; i++) {
            creditBalance = repaymentCreditBalance(loanRepayment);
            percentOfAmount = getPercent(creditBalance,percentage);
            percentageOfAmount = repaymentPercentageOfAmount();
            totalPayment = (percentageOfAmount + loanRepayment);
            repayments.add(new Repayment((long) creditBalance, (long) percentageOfAmount,(long)loanRepayment, (long)totalPayment));
            inputMoney = (long) creditBalance;

        }

    }
    private  float repaymentCreditBalance(float loanPayment){
        return (inputMoney - loanPayment);
    }
    private  float repaymentLoanRepayment(float creditBalance){
        return (creditBalance / monthOfCredit);
    }
    private  float repaymentPercentageOfAmount(){
        if(monthOfCredit>99){
            return (percentOfAmount / (monthOfCredit/10));
        }else {
            return (percentOfAmount / (monthOfCredit));
        }
    }
    private  float repaymentTotalPayment(float creditBalance){
        return (repaymentPercentageOfAmount() + repaymentLoanRepayment(creditBalance));
    }
}

