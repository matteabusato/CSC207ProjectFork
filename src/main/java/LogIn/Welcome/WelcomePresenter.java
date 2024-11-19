package LogIn.Welcome;

public class WelcomePresenter {
    private final WelcomeView welcomeView;

    public WelcomePresenter(WelcomeController controller) {
        this.welcomeView = new WelcomeView(controller);
    }

    public void showView(){
        welcomeView.setVisible(true);
    }

    public void disposeView(){
        welcomeView.setVisible(false);
        welcomeView.dispose();
    }
}