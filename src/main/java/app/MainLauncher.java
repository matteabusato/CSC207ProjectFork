package app;

import data_access.DBDataAccessObject;
import entity.ObjectFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.buyhouse.BuyHouseViewModel;
import interface_adapter.exchangecurrency.ExchangeCurrencyViewModel;
import interface_adapter.findatm.FindAtmViewModel;
import interface_adapter.getloan.GetLoanViewModel;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.maketransaction.MakeTransactionViewModel;
import interface_adapter.manageasset.ManageAssetViewModel;
import interface_adapter.managecard.ManageCardViewModel;
import interface_adapter.manageinsurance.ManageInsuranceViewModel;
import interface_adapter.manageloans.ManageLoansViewModel;
import interface_adapter.seetransactions.SeeTransactionsViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

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
        final BuyHouseViewModel buyHouseViewModel = new BuyHouseViewModel();
        final ExchangeCurrencyViewModel exchangeCurrencyViewModel = new ExchangeCurrencyViewModel();
        final FindAtmViewModel findAtmViewModel = new FindAtmViewModel();
        final GetLoanViewModel getLoanViewModel = new GetLoanViewModel();
        final LoggedinViewModel loggedInViewModel = new LoggedinViewModel();
        final LoginViewModel loginViewModel = new LoginViewModel();
        final MakeTransactionViewModel makeTransactionViewModel = new MakeTransactionViewModel();
        final ManageAssetViewModel manageAssetViewModel = new ManageAssetViewModel();
        final ManageCardViewModel manageCardViewModel = new ManageCardViewModel();
        final ManageInsuranceViewModel manageInsuranceViewModel = new ManageInsuranceViewModel();
        final ManageLoansViewModel manageLoansViewModel = new ManageLoansViewModel();
        final SeeTransactionsViewModel seeTransactionsViewModel = new SeeTransactionsViewModel();
        final SignupViewModel signupViewModel = new SignupViewModel();
        final WelcomeViewModel welcomeViewModel = new WelcomeViewModel();

        final DBDataAccessObject userDataAccessObject = new DBDataAccessObject(new ObjectFactory());

        final BuyHouseView buyHouseView = BuyHouseUseCaseFactory.create();
        views.add(buyHouseView, buyHouseView.getViewName());
        final ExchangeCurrencyView exchangeCurrencyView = ExchangeCurrencyUseCaseFactory.create();
        views.add(exchangeCurrencyView, exchangeCurrencyView.getViewName());
        final FindAtmView findAtmView = FindAtmUseCaseFactory.create();
        views.add(findAtmView, findAtmView.getViewName());
        final GetLoanView getLoanView = GetLoanUseCaseFactory.create();
        views.add(getLoanView, getLoanView.getViewName());
        final LoggedinView loggedinView = LoggedInUseCaseFactory.create();
        views.add(loggedinView, loggedinView.getViewName());
        final LoginView loginView = LoggedInUseCaseFactory.create();
        views.add(loginView, loginView.getViewName());
        final MakeTransactionView makeTransactionView = MakeTransactionUseCaseFactory.create();
        views.add(makeTransactionView, makeTransactionView.getViewName());
        final ManageAssetView manageAssetView = ManageAssetUseCaseFactory.create();
        views.add(manageAssetView, manageAssetView.getViewName());
        final ManageCardView manageCardView = ManageCardUseCaseFactory.create();
        views.add(manageCardView, manageCardView.getViewName());
        final ManageInsuranceView manageInsuranceView = ManageInsuranceUseCaseFactory.create();
        views.add(manageInsuranceView, manageInsuranceView.getViewName());
        final ManageLoansView manageLoansView = ManageLoansUseCaseFactory.create();
        views.add(manageLoansView, manageLoansView.getViewName());
        final SeeTransactionsView seeTransactionsView = SeeTransactionsUseCaseFactory.create();
        views.add(seeTransactionsView, seeTransactionsView.getViewName());
        final WelcomeView welcomeView = WelcomeUseCaseFactory.create();
        views.add(welcomeView, welcomeView.getViewName());


        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}