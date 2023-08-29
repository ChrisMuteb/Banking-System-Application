import org.example.model.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class BankAccountTest {

    @Test
    @DisplayName("Withdraw 300 successfully")
    public void testDebit(){
        BankAccount bankAccount = new BankAccount("Peter", 500, -200);
        bankAccount.debit(300);
        Assertions.assertEquals(200, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Successful top up")
    public void testTopUp(){
        BankAccount bankAccount = new BankAccount("Sandra", 600, -1000);
        bankAccount.topUp(300);
        Assertions.assertEquals(900, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Test debit for negative value")
    public void testDebitNotStuckAtZero(){
        BankAccount bankAccount = new BankAccount("Peter", 80, -200);
        bankAccount.debit(120);
        Assertions.assertEquals(-40, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Test activation account after creation")
    public void testActive(){
        BankAccount bankAccount = new BankAccount("Sasha", 10, -20);
        Assertions.assertTrue(bankAccount.isActive());
    }

    @Test
    @DisplayName("Test account user name")
    public void testAccountUserName(){
        BankAccount bankAccount = new BankAccount("Peter", 500, -200);
        Assertions.assertNotNull(bankAccount.getAccountUserName());
    }

    @Test
    @DisplayName("Test that we can't withdraw below the overdraft")
    public void testNoDebitBelowOverdraft(){
        BankAccount bankAccount = new BankAccount("Peter", 500, -200);
        Assertions.assertThrows(RuntimeException.class, ()->bankAccount.debit(1000));
    }

    @Test
    @DisplayName("Test speed deposit")
    public void testDepositTimeout(){
        BankAccount bankAccount = new BankAccount("Peter", 500, -200);
        Assertions.assertTimeout(Duration.ofNanos(5), ()->bankAccount.topUp(5000));
    }
}
