package Loan;

import javax.swing.*;
import java.text.DecimalFormat;

public class LoanController {
    private double loanAmount = 0;
    private final LoanPresenter loanPresenter;

    public LoanController() {
        loanPresenter = new LoanPresenter(this);
    }

    public void requestLoan(String amountText) {
        try {
            double amount = Double.parseDouble(amountText);
            loanAmount += amount;
            loanPresenter.updateLoanBalance(loanAmount);
        } catch (NumberFormatException ex) {
            loanPresenter.showErrorMessage("Please enter a valid amount.");
        }
    }

    public void launch() {
        loanPresenter.showView();
    }
}
