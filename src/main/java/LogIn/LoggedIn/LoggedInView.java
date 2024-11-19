package LogIn.LoggedIn;

import Brokerage.BrokerageController;
import Brokerage.BrokeragePresenter;
import DataObjects.User;
import LogIn.Welcome.WelcomePresenter;
import Transaction.MakeTransactionPresenter;
import Transaction.TransactionHistoryPresenter;

import javax.swing.*;
import java.awt.*;

public class LoggedInView {
    public LoggedInView(LoggedInController controller) {
        this.user = user;

        setTitle("Logged In View");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Create a combined panel for the top section
        JPanel topPanel = new JPanel(new BorderLayout());

        // Logout Button in the top-right corner
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(80, 25));
        logoutPanel.add(logoutButton);
        topPanel.add(logoutPanel, BorderLayout.EAST);

        // User Info in the top-left
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel accountLabel = new JLabel("UserID: " + user.getUserID());
        JLabel balanceLabel = new JLabel("Balance: $" + user.getBalance());

        infoPanel.add(welcomeLabel);
        infoPanel.add(accountLabel);
        infoPanel.add(balanceLabel);
        topPanel.add(infoPanel, BorderLayout.WEST);

        // Add the topPanel to the NORTH region
        add(topPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton sendMoneyButton = new JButton("Send Money");
        sendMoneyButton.addActionListener(e -> {
            new MakeTransactionPresenter(user).setVisible(true);
            dispose();
        });

        JButton transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(e -> {
            new TransactionHistoryPresenter(user).setVisible(true);
            dispose();
        });

        JButton cardsButton = new JButton("Cards");
        cardsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Cards functionality"));

        JButton atmsButton = new JButton("ATMs near me");
        atmsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Find ATMs near you functionality"));

        JButton assetsButton = new JButton("Assets");
        assetsButton.addActionListener(e -> {
            BrokeragePresenter view = new BrokeragePresenter();
            view.setVisible(true);
            new BrokerageController(view);
            dispose();
        });

        JButton loansButton = new JButton("Loans");
        loansButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Loans functionality"));

        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(cardsButton);
        buttonPanel.add(atmsButton);
        buttonPanel.add(assetsButton);
        buttonPanel.add(loansButton);

        add(buttonPanel, BorderLayout.CENTER);

        logoutButton.addActionListener(e -> {
            new WelcomePresenter().setVisible(true);
            dispose();
        });
    }
}
