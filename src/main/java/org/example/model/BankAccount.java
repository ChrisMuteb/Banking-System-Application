package org.example.model;

public class BankAccount {
    private String accountNumber;
    private String accountUserName;
    private double accountBalance;
    private double overdraft;
    private String cardNumber;
    private boolean isActive;

    public BankAccount() {
    }

    public BankAccount(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BankAccount(String accountUserName, double balance, double overdraft) {
        this.accountUserName = accountUserName;
        this.accountBalance = balance;
        this.overdraft = overdraft;
        this.isActive = true;
    }

    public double topUp(double amount){
        return this.accountBalance += amount;
    }
    public double debit(double amount){

        if(this.accountBalance - amount > this.overdraft){
            this.accountBalance -= amount;
            return amount;
        }
        else
            throw new RuntimeException();
    }

    public double getBalance(){
        return this.accountBalance;
    }

    public double getOverdraft(){
        return this.overdraft;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getAccountUserName() {
        return accountUserName;
    }
}
