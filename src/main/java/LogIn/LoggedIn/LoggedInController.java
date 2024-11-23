package LogIn.LoggedIn;

import ATM.ATMMap.ATMMapController;
import App.ControllerInterface;
import DataObjects.UserObject;
import House.HouseMap.HouseMapController;
import LogIn.Welcome.WelcomeController;
import Transaction.MakeTransaction.MakeTransactionController;
import Transaction.SeeTransactionHistory.SeeTransactionHistoryController;

public class LoggedInController implements ControllerInterface {
    UserObject loggedInUser;
    private LoggedInPresenter loggedInPresenter;
    private WelcomeController welcomeController;
    private MakeTransactionController makeTransactionController;
    private SeeTransactionHistoryController seeTransactionHistoryController;
    private HouseMapController houseMapController;
    private ATMMapController atmMapController;

    public LoggedInController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.makeTransactionController = new MakeTransactionController(loggedInUser);
        this.seeTransactionHistoryController = new SeeTransactionHistoryController(loggedInUser);
        this.houseMapController = new HouseMapController(loggedInUser,  this);
        this.atmMapController = new ATMMapController(loggedInUser, this);

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
        //buyAssetsController.launch();
    }

    public void houseMapTriggered() {
        loggedInPresenter.disposeView();
        houseMapController.launch();
    }

    public void atmMapTriggered() {
        loggedInPresenter.disposeView();
        atmMapController.launch();
    }

    public void exchangesTriggered() {
        loggedInPresenter.disposeView();
        //exchangesController.launch();
    }
}

