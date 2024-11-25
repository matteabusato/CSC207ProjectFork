package Transaction.DataObject;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import UserDataObject.UserObject;
import UserDataObject.UsersController;

import java.nio.file.FileSystems;
import java.util.List;

public class TransactionDBAccess implements DataAccessInterface<TransactionObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, TransactionObject transaction) {
        int senderID = userID;
        int receiverID = transaction.receiverID;
        UserObject sender = usersController.getUser(senderID);
        double amount = transaction.amount;

        List<TransactionObject> transactions = controller.readData(sender.getFileDirectory() + FileSystems.getDefault().getSeparator() + "TransactionHistory.json", TransactionObject.class);
        transactions.add(transaction);
        controller.saveData(sender.getFileDirectory() + FileSystems.getDefault().getSeparator() + "TransactionHistory.json", transactions, TransactionObject.class);

        sender = updateSenderBalance(senderID, amount);
        updateReceiverBalance(receiverID, amount);

        return sender;
    }

    @Override
    public List<TransactionObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator() + "TransactionHistory.json", TransactionObject.class);
    }

    private UserObject updateSenderBalance(int userID, double amount){
        UserObject user = usersController.getUser(userID);
        user.setBalance(user.getBalance() - amount);
        usersController.changeUser(user.getUserID(), user);
        return user;
    }

    private void updateReceiverBalance(int userID, double amount){
        if (usersController.checkUserExistance(userID)){
            UserObject user = usersController.getUser(userID);
            user.setBalance(user.getBalance() + amount);
            usersController.changeUser(user.getUserID(), user);
        }
    }
}