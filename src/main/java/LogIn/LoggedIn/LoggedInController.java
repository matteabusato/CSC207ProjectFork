package LogIn.LoggedIn;

import Brokerage.BrokerageController;
import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.Welcome.WelcomeController;
import Transaction.MakeTransaction.MakeTransactionController;
import Transaction.SeeTransactionHistory.SeeTransactionHistoryController;

public class LoggedInController implements ControllerInterface {
    UserObject loggedInUser;
    private LoggedInPresenter loggedInPresenter;
    private WelcomeController welcomeController;
    private MakeTransactionController makeTransactionController;
    private SeeTransactionHistoryController seeTransactionHistoryController;
    private BrokerageController brokerageController;

    public LoggedInController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.makeTransactionController = new MakeTransactionController(loggedInUser);
        this.seeTransactionHistoryController = new SeeTransactionHistoryController(loggedInUser);
        this.brokerageController = new BrokerageController(loggedInUser);

        // at last
        this.loggedInPresenter = new LoggedInPresenter(this);
    }

    @Override
    public void launch(){
        loggedInPresenter.showView();
    }

    public void logOutTriggered() {
        loggedInPresenter.disposeView();
        welcomeController.launch();
    }

    public void sendMoneyTriggered() {
        loggedInPresenter.disposeView();
        makeTransactionController.launch();
    }

    public void seeTransactionHistoryTriggered() {
        loggedInPresenter.disposeView();
        seeTransactionHistoryController.launch();
    }

    public void buyAssetsTriggered() {
        loggedInPresenter.disposeView();
        brokerageController.launch();
    }
}

