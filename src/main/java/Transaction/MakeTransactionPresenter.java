package Transaction;

import DataObjects.User;
import DataObjects.UsersController;
import LogIn.LoggedInPresenter;
import LogIn.WelcomePresenter;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class MakeTransactionPresenter extends JFrame {
    private User user;
    private JTextField cardField;
    private JTextField receiverIDField;
    private JTextField amountField;
    private JButton processButton;

    public MakeTransactionPresenter(User user) {
        this.user = user;

        setTitle("Make Transaction");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(80, 25)); // Small size
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.NORTH);

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

        add(transactionPanel, BorderLayout.CENTER);

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
                    } else if (amount > user.getBalance()) {
                        JOptionPane.showMessageDialog(this, "Amount should be less or equal than the current balance", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                    } else {
                        TransactionController transactionController = new TransactionController();

                        UsersController usersController = new UsersController();
                        User receiver = usersController.getUser(receiverID);

                        TransactionObject transaction = new TransactionObject(user.getUserID(), receiver.getUserID(), cardNumber, amount, LocalDateTime.now());
                        transactionController.addTransaction(user, transaction);

                        JOptionPane.showMessageDialog(this, "Transaction Successful!", "Transaction", JOptionPane.INFORMATION_MESSAGE);
                        new LoggedInPresenter(user).setVisible(true);
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        logoutButton.addActionListener(e -> {
            new WelcomePresenter().setVisible(true);
            dispose();
        });
    }
}
