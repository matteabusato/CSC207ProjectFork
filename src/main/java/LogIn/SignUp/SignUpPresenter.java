package LogIn.SignUp;

public class SignUpPresenter{
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
