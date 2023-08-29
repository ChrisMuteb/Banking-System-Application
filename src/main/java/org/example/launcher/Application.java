package org.example.launcher;

import org.example.model.BankAccount;
import org.example.service.ATM;
import org.example.service.ATMDebit;

public class Application {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Peter", 200, -500);

        Thread producer = new Thread(new ATM(bankAccount));
        Thread consumer = new Thread(new ATMDebit(bankAccount));


        producer.start();
        consumer.start();


    }
}