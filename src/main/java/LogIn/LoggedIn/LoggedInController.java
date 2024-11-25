package LogIn.LoggedIn;

import ATM.ATMMap.ATMMapController;
import Card.CardController;
import App.ControllerInterface;
import DataObjects.UserObject;
import Exchange.CurrencyExchangeController;
import House.HouseMap.HouseMapController;
import LogIn.Welcome.WelcomeController;
import Transaction.MakeTransaction.MakeTransactionController;
import Transaction.PopUpTransaction.PopUpTransactionController;
import Transaction.SeeTransactionHistory.SeeTransactionHistoryController;

public class LoggedInController implements ControllerInterface {
    UserObject loggedInUser;
    private LoggedInPresenter loggedInPresenter;
    private WelcomeController welcomeController;
    private MakeTransactionController makeTransactionController;
    private SeeTransactionHistoryController seeTransactionHistoryController;
    private CardController cardController;
    private CurrencyExchangeController exchangeController;
    private HouseMapController houseMapController;
    private ATMMapController atmMapController;
    private PopUpTransactionController popUpTransactionController;

    public LoggedInController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.makeTransactionController = new MakeTransactionController(user);
        this.seeTransactionHistoryController = new SeeTransactionHistoryController(user);
        this.cardController = new CardController(user);
        this.exchangeController = new CurrencyExchangeController(user);
        this.houseMapController = new HouseMapController(user, this);
        this.atmMapController = new ATMMapController(user, this);
        this.popUpTransactionController = new PopUpTransactionController(user, this);
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

    public void cardTriggered() {
        loggedInPresenter.disposeView();
        cardController.launch();
    }

    public void exchangeTriggered() {
        loggedInPresenter.disposeView();
        exchangeController.launch();
    }

    public void atmMapTriggered() {
        loggedInPresenter.disposeView();
        atmMapController.launch();
    }

    public void houseMapTriggered() {
        loggedInPresenter.disposeView();
        houseMapController.launch();
    }

    public void popUpTransaction(double amount, String type) {
        popUpTransactionController.launch();
        popUpTransactionController.makeTransaction(amount, type);
    }

    public HouseMapController getHouseMapController() {
        return houseMapController;
    }
}

