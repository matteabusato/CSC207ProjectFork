package Transaction.SeeTransactionHistory;

import App.ControllerInterface;
import login.loggedin.LoggedInController;
import UserDataObject.UserObject;
import Transaction.DataObject.TransactionController;
import Transaction.DataObject.TransactionObject;

import java.util.List;

public class SeeTransactionHistoryController implements ControllerInterface {
    UserObject loggedInUser;
    List<TransactionObject> transactions;
    private SeeTransactionHistoryPresenter seeTransactionHistoryPresenter;
    private TransactionController transactionController;

    public SeeTransactionHistoryController(UserObject user) {
        this.loggedInUser = user;
        this.transactionController = new TransactionController();
        this.transactions = transactionController.getAllTransactions(loggedInUser.getUserID());
        this.seeTransactionHistoryPresenter = new SeeTransactionHistoryPresenter(this);
    }

    @Override
    public void launch(){
        seeTransactionHistoryPresenter.showView();
    }

    public void backTriggered(){
        seeTransactionHistoryPresenter.disposeView();
        LoggedInController loggedInController = new LoggedInController(loggedInUser);
        loggedInController.launch();
    }
}
