package org.example.service;

import org.example.model.BankAccount;

public class ATMDebit implements Runnable{
    BankAccount bankAccount;

    public ATMDebit(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        while (true){
            bankAccount.debit(5);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
