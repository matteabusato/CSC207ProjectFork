package LogIn;

import DataObjects.UsersController;


public class SignUpController {
    private SignUpPresenter signUpPresenter = new SignUpPresenter();
    private LoggedInPresenter loggedInPresenter = new LoggedInPresenter()
    UsersController usersController = new UsersController();
    
    public SignUpController() {
    }

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
        loggedInPresenter.launch();
    }
}
