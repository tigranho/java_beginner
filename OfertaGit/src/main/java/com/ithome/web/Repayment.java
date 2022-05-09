package com.ithome.web;

public class Repayment {
    private long creditBalance;
    private long percentageOfAmount;
    private long loanRepayment;
    private long totalPayment;

    public Repayment(long creditBalance, long percentageOfAmount, long loanRepayment, long totalPayment) {
        this.creditBalance = creditBalance;
        this.percentageOfAmount = percentageOfAmount;
        this.loanRepayment = loanRepayment;
        this.totalPayment = totalPayment;
    }

    public Repayment(long creditBalance, long percentageOfAmount, long loanRepayment) {
        this.creditBalance = creditBalance;
        this.percentageOfAmount = percentageOfAmount;
        this.loanRepayment = loanRepayment;
    }


    public long getCreditBalance() {
        return creditBalance;
    }

    private void setCreditBalance(long creditBalance) {
        this.creditBalance = creditBalance;
    }

    public long getPercentageOfAmount() {
        return percentageOfAmount;
    }

    private void setPercentageOfAmount(long percentageOfAmount) {
        this.percentageOfAmount = percentageOfAmount;
    }

    public long getLoanRepayment() {
        return loanRepayment;
    }

    private void setLoanRepayment(long loanRepayment) {
        this.loanRepayment = loanRepayment;
    }

    public long getTotalPayment() {
        return totalPayment;
    }

    private void setTotalPayment(long totalPayment) {
        this.totalPayment = totalPayment;
    }

}
