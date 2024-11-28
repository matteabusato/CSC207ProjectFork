package app;

import interface_adapter.ViewManagerModel;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainLauncher class to start the application.
 * This class contains the entry point of the application.
*/
public class MainLauncher {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    /**
     * The main method for starting the program with an external database used to persist user data.
     * @param args input to main
     */
    public static void main(String[] args) {
        final JFrame application = new JFrame("Start");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        application.setLocationRelativeTo(null);

        final CardLayout cardLayout = new CardLayout();

        // Various View objects.
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Manages what view is showing.
        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views
        final WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        final LoginViewModel loginViewModel = new LoginViewModel();
        final LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        final SignupViewModel signupViewModel = new SignupViewModel();


        final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(new CommonUserFactory());

        final WelcomeView welcomeView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                welcomeViewModel, userDataAccessObject);
        views.add(welcomeView, welcomeView.getViewName());

        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());

        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
                loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.getViewName());

        final LoggedInView loggedInView = ChangePasswordUseCaseFactory.create(viewManagerModel,
                loggedInViewModel, userDataAccessObject);
        views.add(loggedInView, loggedInView.getViewName());

        // TODO: add all views

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}