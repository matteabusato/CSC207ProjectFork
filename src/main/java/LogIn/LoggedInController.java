package LogIn;

import DataObjects.User;
import DataObjects.UsersController;

public class LoggedInController {
    // add all the other controllers for all functionalities
    private LoggedInPresenter
    UsersController usersController = new UsersController();
    User loggedInUser;

    public LoggedInController(User user) {
        this.loggedInUser = user;
    }

    public void launch(){
        loggedInPresenter.showView();
    }

}

