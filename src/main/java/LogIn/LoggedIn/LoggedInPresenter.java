package LogIn.LoggedIn;

import javax.swing.*;

public class LoggedInPresenter extends JFrame {
    private final LoggedInView loggedInView;

    public LoggedInPresenter(LoggedInController controller){
        this.loggedInView = new LoggedInView(controller);
    }

    public void showView(){
        loggedInView.setVisible(true);
    }

    public void disposeView(){
        loggedInView.setVisible(false);
        loggedInView.dispose();
    }
}
