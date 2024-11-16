package Transaction;

import DataAccess.DataAccessController;
import DataObjects.User;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        User user = new User("tea", "12345", "Mattea", "Busato", 123, 123456.3);

        TransactionObject transaction = new TransactionObject(123, "Alberto", "Barbara", "MasterCard1", 134.2, LocalDateTime.now());
        TransactionController controller = new TransactionController();
        controller.addFirstTransaction(user, transaction);  // This will call the method to save the first transaction.

        TransactionObject transaction2 = new TransactionObject(124, "Barbara", "Alberto", "MasterCard2", 134.2, LocalDateTime.now());
        controller.addTransaction(user, transaction2);
    }
}
