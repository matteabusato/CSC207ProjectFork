package Transaction;

import DataAccess.DataAccessController;
import DataObjects.User;
import DataObjects.UsersController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TransactionController {

    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    public void addTransaction(User user, TransactionObject transaction) {
        List<TransactionObject> transactions = controller.readData(user.getFileDirectory() + "TransactionHistory.json", TransactionObject.class);
        transactions.add(transaction);
        controller.saveData(user.getFileDirectory() + "TransactionHistory.json", transactions, TransactionObject.class);

        int userID = user.getUserID();
        user.setBalance(user.getBalance() - transaction.getAmount());
        usersController.changeUser(userID, user);

        int receiverID = transaction.getReceiverID();
        User receiver = usersController.getUser(receiverID);
        receiver.setBalance(receiver.getBalance() + transaction.getAmount());
        usersController.changeUser(receiverID, receiver);
    }

    public List<TransactionObject> showTransactionHistory(User user) {
        List<TransactionObject> transactions = controller.readData(user.getFileDirectory() + "TransactionHistory.json", TransactionObject.class);
        return transactions;
    }
}
