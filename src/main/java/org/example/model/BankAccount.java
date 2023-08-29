package org.example.model;

public class BankAccount {
    private String accountNumber;
    private String accountUserName;
    private double accountBalance;
    private double overdraft;
    private String cardNumber;
    private boolean isActive;
    private boolean amountSet;

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
        amountSet = false;
    }

    public synchronized double topUp(double amount){
        while (amountSet){
            try {
                wait();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        amountSet = true;
        this.accountBalance += amount;
        System.out.println("Top up: " + amount + " tOTAL AMOUNT " + accountBalance);
        notify();
        return accountBalance;
    }
    public synchronized double debit(double amount){

        while (!amountSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(this.accountBalance - amount > this.overdraft){
            this.accountBalance -= amount;
            System.out.println("Debit: " + amount + " tOTAL AMOUNT " + accountBalance);
            amountSet = false;
            notify();
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
