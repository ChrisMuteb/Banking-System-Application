import org.example.model.BankAccount;
import org.example.service.ATM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountThreadTest {

    @Test
    @DisplayName("Testing for multiple thread accessing resources")
    public void testMultiThreadingTopup(){
        BankAccount bankAccount = new BankAccount("Peter", 200, -500);

        Thread ATM1 = new Thread(new ATM(bankAccount));
        Thread ATM2 = new Thread(new ATM(bankAccount));
        Thread ATM3 = new Thread(new ATM(bankAccount));

        ATM1.start();
        ATM2.start();
        ATM3.start();

        try{
            ATM1.join();
            ATM2.join();
            ATM3.join();
        }catch (InterruptedException e){}

//        System.out.println("Current balance: " + bankAccount.getBalance());
        Assertions.assertEquals(500, bankAccount.getBalance());
    }


}
