package Views;

import DataObjects.User;
import DataObjects.UsersController;
import Transaction.TransactionController;
import Transaction.TransactionObject;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class MakeTransactionView extends JFrame {
    User user;
    private JTextField cardField;
    private JTextField receiverIDField;
    private JTextField amountField;
    private JButton processButton;

    public MakeTransactionView(User user) {
        this.user = user;

        setTitle("Make Transaction");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel cardLabel = new JLabel("Card Number:");
        cardField = new JTextField();
        JLabel receiverIDLabel = new JLabel("Receiver UserID:");
        receiverIDField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        processButton = new JButton("Process Transaction");

        transactionPanel.add(cardLabel);
        transactionPanel.add(cardField);
        transactionPanel.add(receiverIDLabel);
        transactionPanel.add(receiverIDField);
        transactionPanel.add(amountLabel);
        transactionPanel.add(amountField);
        transactionPanel.add(new JLabel());
        transactionPanel.add(processButton);

        add(transactionPanel);

        processButton.addActionListener(e -> {
            String cardNumber = cardField.getText();
            String receiverIDString = receiverIDField.getText();
            String amountString = amountField.getText();

            if (cardNumber.isEmpty() || receiverIDString.isEmpty() || amountString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int receiverID = Integer.parseInt(receiverIDString);
                    double amount = Double.parseDouble(amountString);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(this, "Amount should be greater than zero.", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                    } else if (amount >= user.getBalance()) {
                        JOptionPane.showMessageDialog(this, "Amount should be less or equal than the current balance", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                    } else {
                        UsersController usersController = new UsersController();
                        User receiver = usersController.getUser(receiverID);
                        TransactionController transactionController = new TransactionController();
                        TransactionObject transaction = new TransactionObject(user.getUserID(), receiver.getUserID(), cardNumber, amount, LocalDateTime.now());
                        transactionController.addTransaction(user, transaction);
                        // TODO: SWITCH TO TRANSACTION HISTORY VIEW
                        JOptionPane.showMessageDialog(this, "Transaction Successful!", "Transaction", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
