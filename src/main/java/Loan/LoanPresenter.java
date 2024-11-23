package Loan;

public class LoanPresenter {
    private final LoanView loanView;

    public LoanPresenter(LoanController controller) {
        loanView = new LoanView(controller);
    }

    public void showView() {
        loanView.setVisible(true);
    }

    public void updateLoanBalance(double balance) {
        loanView.updateLoanBalanceLabel(balance);
    }

    public void showErrorMessage(String message) {
        loanView.showErrorDialog(message);
    }
}
