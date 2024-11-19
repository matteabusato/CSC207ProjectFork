package LogIn;

public class LogInPresenter{
    private final LogInView logInView;

    public LogInPresenter(LogInController controller){
        this.logInView = new LogInView(controller);
    }

    public void showView(){
        logInView.setVisible(true);
    }

    public void disposeView(){
        logInView.setVisible(false);
        logInView.dispose();
    }
}
