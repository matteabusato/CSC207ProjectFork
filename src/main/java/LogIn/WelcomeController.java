package LogIn;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WelcomeController {
    private WelcomePresenter welcomePresenter = new WelcomePresenter(this);
    private LogInController logInController = new LogInController();
    private SignUpController signUpController = new SignUpController();

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
