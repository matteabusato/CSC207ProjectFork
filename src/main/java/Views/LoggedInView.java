package Views;

import DataObjects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInView extends JFrame {
    private User user;

    public LoggedInView(User user) {
        this.user = user;

        setTitle("Logged In View");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel accountLabel = new JLabel("UserID: " + user.getUserID());
        JLabel balanceLabel = new JLabel("Balance: $" + user.getBalance());

        infoPanel.add(welcomeLabel);
        infoPanel.add(accountLabel);
        infoPanel.add(balanceLabel);

        add(infoPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton sendMoneyButton = new JButton("Send Money");
        sendMoneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MakeTransactionView(user).setVisible(true);
                dispose();
            }
        });

        JButton transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Transactions functionality");
            }
        });

        JButton cardsButton = new JButton("Cards");
        cardsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cards functionality");
            }
        });

        JButton atmsButton = new JButton("ATMs near me");
        atmsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Find ATMs near you functionality");
            }
        });

        JButton assetsButton = new JButton("Assets");
        assetsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Assets functionality");
            }
        });

        JButton loansButton = new JButton("Loans");
        loansButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Loans functionality");
            }
        });

        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(cardsButton);
        buttonPanel.add(atmsButton);
        buttonPanel.add(assetsButton);
        buttonPanel.add(loansButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
