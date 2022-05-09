package com.ithome.web;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HashivCon {
    FirstReplayment replayment = new FirstReplayment();

    public static List<FirstReplayment> displayMonthlyBalance(long loanAmount,
                                                              int termInYears, double interestRate, double monthlyPayment) {

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        List<FirstReplayment> hashiv = new ArrayList<>();
        interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;
        int termInMonths = termInYears;

        // Loop through the term of the loan tracking the balance
        double balance = loanAmount;
        for (int i = 0; i < termInMonths - 1; i++) {

            // Add interest to the balanc
            balance += (balance * monthlyRate);
            // Subtract the monthly payment
            balance -= monthlyPayment;
            // Display running balance
            long second = (long) (balance * (monthlyRate));
            long third = (long) (monthlyPayment - (balance * (monthlyRate)));
            if (i == 0) {
                System.out.println("Balance after payment " + (i + 1) + ": " +
                        loanAmount + " " + ((loanAmount * (monthlyRate)) + " " + (monthlyPayment - (loanAmount * (monthlyRate)) + " " + monthlyPayment)));
                hashiv.add(new FirstReplayment(loanAmount, (long) (loanAmount * (monthlyRate)), (long) (monthlyPayment - (loanAmount * (monthlyRate))), (long) monthlyPayment));
            }
            System.out.println("Balance after payment " + (i + 1) + ": " +
                    currencyFormat.format(balance) + " " + ((balance * (monthlyRate)) + " " + (monthlyPayment - (balance * (monthlyRate)) + " " + monthlyPayment)));
            hashiv.add(new FirstReplayment((long) balance, second, third, (long) monthlyPayment));

        }


        return hashiv;

    }

    public static double calculateMonthlyPayment(long loanAmount, int termInYears, double interestRate) {
        interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;
        int termInMonths = termInYears;
        double monthlyPayment = (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
        return monthlyPayment;
    }

    public static List<FirstReplayment> hashiv(long amount, float percentage, float months) {
        List<FirstReplayment> hashiv = new ArrayList<>();
        long loanAmount = amount;
        int termInYears = (int) months;
        double interestRate = percentage;
        double monthlyPayment = calculateMonthlyPayment(loanAmount, termInYears, interestRate);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat interestFormat = NumberFormat.getPercentInstance();
        hashiv = displayMonthlyBalance(loanAmount, termInYears, interestRate, monthlyPayment);
        return hashiv;
    }
}

