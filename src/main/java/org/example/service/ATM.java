package org.example.service;

import org.example.model.BankAccount;

public class ATM implements Runnable{
    BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        double amt = 22;

        while (true){
            bankAccount.topUp(100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
