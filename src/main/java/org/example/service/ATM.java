package org.example.service;

import org.example.model.BankAccount;

public class ATM implements Runnable{
    BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        bankAccount.topUp(100);
    }
}
