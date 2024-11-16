package Transaction;

import DataAccess.DataAccessController;
import DataObjects.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TransactionController {

    DataAccessController controller = new DataAccessController();

    public void addFirstTransaction(User user, TransactionObject transaction) {
        List<TransactionObject> transactions = new ArrayList<>();
        transactions.add(transaction);
        controller.saveData(user.getFileName(), transactions, TransactionObject.class);
    }

    public void addTransaction(User user, TransactionObject transaction) {
        List<TransactionObject> transactions = controller.readData(user.getFileName(), TransactionObject.class);
        transactions.add(transaction);
        controller.saveData(user.getFileName(), transactions, TransactionObject.class);
    }
}
