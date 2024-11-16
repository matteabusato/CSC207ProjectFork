package Transaction;

import DataAccess.DataAccessController;
import DataObjects.User;
import DataObjects.UsersController;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        User user = new User(12345, "Mattea", "Busato", "123", 123456.3);
        TransactionController controller = new TransactionController();

        TransactionObject transaction1 = new TransactionObject(123, 124, "MasterCard1", 134.2, LocalDateTime.now());
        controller.addTransaction(user, transaction1);  // This will call the method to save the first transaction.

        TransactionObject transaction2 = new TransactionObject(124, 123, "MasterCard2", 134.2, LocalDateTime.now());
        controller.addTransaction(user, transaction2);

        UsersController usersController = new UsersController();
        usersController.addUser(user);
    }
}
