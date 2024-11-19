package LogIn.SignUp;

import DataObjects.UsersController;
import LogIn.LoggedIn.LoggedInController


public class SignUpController {
    private SignUpPresenter signUpPresenter = new SignUpPresenter(this);
    private LoggedInController loggedInController = new LoggedInController();
    UsersController usersController = new UsersController();


    public void launch(){
        signUpPresenter.showView();
    }

    public boolean signUpTriggered(String firstName, String lastName, String password){
        if (!(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty())) {
            usersController.addUser(firstName, lastName, password);
            return true;
        }
        return false;
    }

    public void onSignUpSuccess(){
        signUpPresenter.disposeView();
        loggedInController.launch();
    }
}
