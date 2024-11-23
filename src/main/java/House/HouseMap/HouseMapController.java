package House.HouseMap;

import ATM.ATMMap.ATMMapPresenter;
import DataObjects.UserObject;
import Functionality.FunctionalityController;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;

public class HouseMapController extends FunctionalityController {

    UserObject loggedInUser;
    private final HouseMapPresenter houseMapPresenter;
    private final LoggedInController loggedInController;

    public HouseMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        this.houseMapPresenter = new HouseMapPresenter(this);
        this.loggedInController = loggedInController;
    }

    public void launch(){
        houseMapPresenter.showView();
    }

    @Override
    public void back() {
        houseMapPresenter.disposeView();
        loggedInController.launch();
    }
}
