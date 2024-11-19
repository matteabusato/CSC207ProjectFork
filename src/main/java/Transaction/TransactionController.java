package Transaction;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TransactionController implements DataAccessInterface<TransactionObject> {

    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public void saveData(UserObject user, TransactionObject transaction) {
        List<TransactionObject> transactions = controller.readData(user.getFileDirectory() + "TransactionHistory.json", TransactionObject.class);
        transactions.add(transaction);
        controller.saveData(user.getFileDirectory() + "TransactionHistory.json", transactions, TransactionObject.class);

        int userID = user.getUserID();
        user.setBalance(user.getBalance() - transaction.getAmount());
        usersController.changeUser(userID, user);

        int receiverID = transaction.getReceiverID();
        UserObject receiver = usersController.getUser(receiverID);
        receiver.setBalance(receiver.getBalance() + transaction.getAmount());
        usersController.changeUser(receiverID, receiver);
    }

    @Override
    public List<TransactionObject> readData(UserObject user) {
        List<TransactionObject> transactions = controller.readData(user.getFileDirectory() + "TransactionHistory.json", TransactionObject.class);
        return transactions;
    }
}
