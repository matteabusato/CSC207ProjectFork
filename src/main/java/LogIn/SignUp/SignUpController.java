package LogIn.SignUp;

import App.ControllerInterface;
import UserDataObject.UserObject;
import UserDataObject.UsersController;
import LogIn.LoggedIn.LoggedInController;

public class SignUpController implements ControllerInterface {
    private SignUpPresenter signUpPresenter = new SignUpPresenter(this);
    private LoggedInController loggedInController;
    UsersController usersController = new UsersController();

    @Override
    public void launch(){
        signUpPresenter.showView();
    }

    public boolean signUpTriggered(String firstName, String lastName, String password){
        if (!(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty())) {
            return true;
        }
        return false;
    }

    public void onSignUpSuccess(String firstName, String lastName, String password){
        UserObject newUser = usersController.addUser(firstName, lastName, password);
        signUpPresenter.disposeView();
        LoggedInController controller = new LoggedInController(newUser);
        controller.launch();
    }
}
