package House.HouseMap;

import ATM.ATMMap.ATMMapPresenter;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;

public class HouseMapController {

    UserObject loggedInUser;
    private WelcomeController welcomeController;
    private final HouseMapPresenter houseMapPresenter;

    public HouseMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.houseMapPresenter = new HouseMapPresenter(this, loggedInController);
    }

    public void launch(){
        houseMapPresenter.showView();
    }

    public void logOutTriggered(){
        houseMapPresenter.disposeView();
        welcomeController.launch();
    }

    public void logInTriggered() {
        houseMapPresenter.disposeView();

    }
}
