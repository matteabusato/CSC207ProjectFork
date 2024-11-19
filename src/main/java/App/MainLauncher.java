package App;

import LogIn.WelcomeController;
import LogIn.WelcomePresenter;

public class MainLauncher {
    public static void main(String[] args) {
        WelcomeController welcomeController = new WelcomeController();
        welcomeController.launch();
    }
}
