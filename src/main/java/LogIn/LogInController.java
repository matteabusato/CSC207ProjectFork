package LogIn;

import DataObjects.User;
import DataObjects.UsersController;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogInController {
    private LogInPresenter logInPresenter = new LogInPresenter(this);
    private LoggedInController loggedInController = new LoggedInController();
    UsersController usersController = new UsersController();
    User loggedUser = null;

    public void launch(){
        logInPresenter.showView();
    }

    public boolean logInTriggered(int userID, String password){
        User user = usersController.getUser(userID);
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
