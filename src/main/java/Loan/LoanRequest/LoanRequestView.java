package Loan.LoanRequest;

import javax.swing.*;
import java.awt.*;

public class LoanRequestView {
    private final JFrame popupFrame;
    private final JLabel messageLabel;
    private final JButton confirmButton;

    public LoanRequestView() {
        // Create the pop-up frame
        popupFrame = new JFrame("Request Loan");
        popupFrame.setSize(400, 200);
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setLayout(new BorderLayout());
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 1));
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED); // Error messages will appear in red
        contentPanel.add(messageLabel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");
        confirmButton.setEnabled(false); // Initially disabled

        // Cancel button closes the window
        cancelButton.addActionListener(e -> popupFrame.dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Add components to the pop-up frame
        popupFrame.add(contentPanel, BorderLayout.CENTER);
        popupFrame.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Displays the pop-up window
    public void show() {
        popupFrame.setVisible(true);
    }

    // Closes the pop-up window
    public void close() {
        popupFrame.dispose();
    }

    // Updates the message label
    public void setMessage(String message, boolean isError) {
        messageLabel.setForeground(isError ? Color.RED : Color.BLACK);
        messageLabel.setText(message);
    }

    // Enables or disables the Confirm button
    public void setConfirmButtonEnabled(boolean enabled) {
        confirmButton.setEnabled(enabled);
    }

    // Adds an ActionListener to the Confirm button
    public void addConfirmButtonListener(Runnable action) {
        confirmButton.addActionListener(e -> action.run());
    }
}
