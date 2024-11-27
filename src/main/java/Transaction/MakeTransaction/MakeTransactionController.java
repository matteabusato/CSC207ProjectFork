package Transaction.MakeTransaction;

import App.ControllerInterface;
import UserDataObject.UserObject;
import login.loggedin.LoggedInController;
import Transaction.DataObject.TransactionController;

public class MakeTransactionController implements ControllerInterface {
    UserObject loggedInUser;
    private MakeTransactionPresenter makeTransactionPresenter;
    private TransactionController transactionController;

    public MakeTransactionController(UserObject user){
        this.loggedInUser = user;
        this.transactionController = new TransactionController();
        this.makeTransactionPresenter = new MakeTransactionPresenter(this);
    }

    // TODO: add card field from database
    @Override
    public void launch(){
        makeTransactionPresenter.showView();
    }

    public void backTriggered(){
        makeTransactionPresenter.disposeView();
        LoggedInController loggedInController = new LoggedInController(loggedInUser);
        loggedInController.launch();
    }

    public boolean makeTransactionTriggered(String cardUsed, int receiverID, int amount){
        if (amount <= loggedInUser.getBalance() && amount > 0) {
            return true;
        }
        return false;
    }

    public void onMakeTransactonSuccess(String cardUsed, int receiverID, int amount) {
        loggedInUser = transactionController.addTransaction(loggedInUser.getUserID(), receiverID, cardUsed, amount);
        makeTransactionPresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}