package LogIn;

import javax.swing.*;
import java.awt.*;

public class SignUpPresenter extends JFrame {
    private final SignUpView signUpView;

    public SignUpPresenter(SignUpController controller) {
        this.signUpView = new SignUpView(controller);
    }

    public void showView(){
        signUpView.setVisible(true);
    }

    public void disposeView(){
        signUpView.setVisible(false);
        signUpView.dispose();
    }
}
