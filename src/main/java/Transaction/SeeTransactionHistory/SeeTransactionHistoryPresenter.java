package Transaction.SeeTransactionHistory;

import LogIn.SignUp.SignUpController;
import LogIn.SignUp.SignUpView;

public class SeeTransactionHistoryPresenter {
    private final SeeTransactionHistoryView seeTransactionHistoryView;

    public SeeTransactionHistoryPresenter(SeeTransactionHistoryController controller) {
        this.seeTransactionHistoryView = new SeeTransactionHistoryView(controller);
    }

    public void showView(){
        seeTransactionHistoryView.setVisible(true);
    }

    public void disposeView(){
        seeTransactionHistoryView.setVisible(false);
        seeTransactionHistoryView.dispose();
    }
}
