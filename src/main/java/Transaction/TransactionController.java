package Transaction;

import DataAccess.DataAccessController;
import DataObjects.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TransactionController {

    DataAccessController controller = new DataAccessController();

    public void addTransaction(User user, TransactionObject transaction) {
        List<TransactionObject> transactions = controller.readData(user.getFileDirectory() + "TransactionHistory.json", TransactionObject.class);
        transactions.add(transaction);
        controller.saveData(user.getFileDirectory() + "TransactionHistory.json", transactions, TransactionObject.class);
    }

    public void showTransactionHistory(User user) {
        List<TransactionObject> transactions = controller.readData(user.getFileDirectory() + "TransactionHistory.json", TransactionObject.class);
        // display in view object the transactions
    }
}
