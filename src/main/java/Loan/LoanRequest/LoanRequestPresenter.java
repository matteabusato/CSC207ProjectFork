package Loan.LoanRequest;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanRequestPresenter {
    private final LoanRequestView view;

    public LoanRequestPresenter(LoanRequestView view) {
        this.view = view;
    }

    // Validates the inputs and updates the Confirm button and message
    public void validateInputs(String amountText, String termText, String interestRateText) {
        String errorMessage = validate(amountText, termText, interestRateText);
        if (errorMessage.isEmpty()) {
            view.setConfirmButtonEnabled(true);
            view.setMessage("", true); // Clear error
        } else {
            view.setConfirmButtonEnabled(false);
            view.setMessage(errorMessage, true); // Show error
        }
    }

    // Confirms the loan request and calculates the repayment
    public void confirmLoanRequest(String amountText, String termText, String interestRateText) {
        String repaymentMessage = calculateRepayment(amountText, termText, interestRateText);
        view.setMessage(repaymentMessage, false); // Show success message
        view.setConfirmButtonEnabled(false); // Disable Confirm after success
    }

    // Validates the input fields
    private String validate(String amountText, String termText, String interestRateText) {
        try {
            double amount = Double.parseDouble(amountText);
            int term = Integer.parseInt(termText);
            double interestRate = Double.parseDouble(interestRateText);

            if (amount <= 0) {
                return "Amount must be greater than 0.";
            }
            if (term <= 0 || term >= 100) {
                return "Term must be between 1 and 99.";
            }
            if (interestRate < 0) {
                return "Interest rate must be 0 or greater.";
            }
            return ""; // No errors
        } catch (NumberFormatException e) {
            return "All fields must contain valid numbers.";
        }
    }

    // Calculates repayment amount and date
    private String calculateRepayment(String amountText, String termText, String interestRateText) {
        try {
            double amount = Double.parseDouble(amountText);
            int term = Integer.parseInt(termText);
            double interestRate = Double.parseDouble(interestRateText) / 100;

            // Calculate repayment amount
            double repayAmount = amount * term * (1 + interestRate);
            DecimalFormat df = new DecimalFormat("#.##");

            // Calculate repayment date
            LocalDate repaymentDate = LocalDate.now().plusYears(term).minusDays(1);
            String formattedDate = repaymentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return "You confirm to repay $" + df.format(repayAmount) + " by " + formattedDate + ".";
        } catch (Exception e) {
            return "An error occurred while calculating repayment.";
        }
    }
}
