package login.welcome;

import App.ControllerInterface;
import login.logIn.LogInController;
import login.signUp.SignUpController;

public class WelcomeController implements ControllerInterface {
    private WelcomePresenter welcomePresenter = new WelcomePresenter(this);
    private LogInController logInController = new LogInController();
    private SignUpController signUpController = new SignUpController();

    public WelcomeController(){
    }

    @Override
    public void launch(){
        welcomePresenter.showView();
    }

    public void logInTriggered(){
        welcomePresenter.disposeView();
        logInController.launch();
    }

    public void signUpTriggered(){
        welcomePresenter.disposeView();
        signUpController.launch();
    }
}
