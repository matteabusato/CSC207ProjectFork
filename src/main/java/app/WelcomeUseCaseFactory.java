package app;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import view.WelcomeView;

/**
 * This class contains the static factory function for creating the WelcomeView.
 */
public class WelcomeUseCaseFactory {
    private WelcomeUseCaseFactory() {

    }

    public static WelcomeView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
            SignupViewModel signupViewModel, WelcomeViewModel welcomeViewModel,
            WelcomeUserDataAccessInterface welcomeUserDataAccessObject
    ) {

        final WelcomeController welcomeController = createUserWelcomeUseCase(viewManagerModel, loginViewModel,
                                                                            signupViewModel, welcomeViewModel
                                                                            welcomeUserDataAccessObject);

        return new WelcomeView(welcomeController, welcomeViewModel);
    }

    private static WelcomeController createUserWelcomeUseCase(ViewManagerModel viewManagerModel,
                                                              LoginViewModel loginViewModel,
                                                              SignupViewModel signupViewModel,
                                                              WelcomeViewModel welcomeViewModel,
                                                              WelcomeUserDataAccessInterface welcomeUserDataAccessObject
    ) {
        final WelcomeOutputBoundary welcomeOutputBoundary = new WelcomePresenter(viewManagerModel, loginViewModel,
                                                                            signupViewModel, welcomeViewModel);
        final UserFactory userFactory = new UserFactory();

        final WelcomeInputBoundary userWelcomeInteractor = new WelcomeInteractor(
                welcomeUserDataAccessObject, welcomeOutputBoundary, userFactory);

        return new WelcomeController(userWelcomeInteractor);

    }

}
