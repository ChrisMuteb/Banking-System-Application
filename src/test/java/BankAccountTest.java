import org.example.model.BankAccount;
import org.junit.jupiter.api.*;

import java.time.Duration;

public class BankAccountTest {
    static BankAccount bankAccount;
    @BeforeEach
    public void prepTest(){

        bankAccount = new BankAccount("Peter", 500, -200);
    }

    @Test
    @DisplayName("Withdraw 300 successfully")
    public void testDebit(){
        bankAccount.debit(300);
        Assertions.assertEquals(200, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Successful top up")
    public void testTopUp(){
        bankAccount.topUp(300);
        Assertions.assertEquals(800, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Test debit for negative value")
    public void testDebitNotStuckAtZero(){
        bankAccount.debit(600);
        Assertions.assertEquals(-100, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Test activation account after creation")
    public void testActive(){

        Assertions.assertTrue(bankAccount.isActive());
    }

    @Test
    @DisplayName("Test account user name")
    public void testAccountUserName(){
        Assertions.assertNotNull(bankAccount.getAccountUserName());
    }

    @Test
    @DisplayName("Test that we can't withdraw below the overdraft")
    public void testNoDebitBelowOverdraft(){
        Assertions.assertThrows(RuntimeException.class, ()->bankAccount.debit(1000));
    }

    @Test
    @DisplayName("Test speed deposit")
    public void testDepositTimeout(){
        Assertions.assertTimeout(Duration.ofNanos(5), ()->bankAccount.topUp(5000));
    }

    @AfterAll
    public static void endTest(){
        System.out.println("Bye!");
    }
}
