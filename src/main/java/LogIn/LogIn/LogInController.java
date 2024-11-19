package LogIn.LogIn;

import DataObjects.UserObject;
import DataObjects.UsersController;
import LogIn.LoggedIn.LoggedInController;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogInController {
    private LogInPresenter logInPresenter = new LogInPresenter(this);
    private LoggedInController loggedInController = new LoggedInController();
    UsersController usersController = new UsersController();
    UserObject loggedUser = null;

    public void launch(){
        logInPresenter.showView();
    }

    public boolean logInTriggered(int userID, String password){
        UserObject user = usersController.getUser(userID);
        if (user != null && user.getPasswordHash().equals(password)){
            loggedUser = user;
            return true;
        }
        return false;
    }

    public void onLoginSuccess() {
        logInPresenter.disposeView();
        loggedInController.launch();
    }
}
