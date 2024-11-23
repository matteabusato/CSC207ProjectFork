package Loan.LoanRequest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoanRequestController {
    private final LoanRequestPresenter presenter;
    private final LoanRequestView view;

    public LoanRequestController(JTextField amountField, JTextField termField, JTextField interestRateField) {
        view = new LoanRequestView();
        presenter = new LoanRequestPresenter(view);

        // Add listeners for Confirm button
        view.addConfirmButtonListener(() -> {
            presenter.confirmLoanRequest(
                    amountField.getText(),
                    termField.getText(),
                    interestRateField.getText()
            );
        });

        // Add input validation listeners
        DocumentListener validationListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInputs();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInputs();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
            }

            private void validateInputs() {
                presenter.validateInputs(
                        amountField.getText(),
                        termField.getText(),
                        interestRateField.getText()
                );
            }
        };

        amountField.getDocument().addDocumentListener(validationListener);
        termField.getDocument().addDocumentListener(validationListener);
        interestRateField.getDocument().addDocumentListener(validationListener);
    }

    public void showLoanRequestPopup() {
        view.show();
    }
}
