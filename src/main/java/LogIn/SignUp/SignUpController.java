package LogIn.SignUp;

import DataObjects.UserObject;
import DataObjects.UsersController;
import LogIn.LoggedIn.LoggedInController;

public class SignUpController {
    private SignUpPresenter signUpPresenter = new SignUpPresenter(this);
    private LoggedInController loggedInController;
    UsersController usersController = new UsersController();

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
        System.out.println(newUser);
        signUpPresenter.disposeView();
        LoggedInController controller = new LoggedInController(newUser);
        System.out.println(controller);
        controller.launch();
    }
}
