package login.loggedin;

import UserDataObject.UserObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class LoggedInView extends JFrame {
    UserObject user;

    public LoggedInView(LoggedInController controller) {
        this.user = controller.getLoggedInUser();

        setTitle("Logged In View");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(refreshPanel, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(80, 25));
        logoutPanel.add(logoutButton);
        topPanel.add(logoutPanel, BorderLayout.EAST);

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

        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JButton sendMoneyButton = new JButton("Send Money");
        sendMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendMoneyTriggered();
            }
        });

        JButton transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.seeTransactionHistoryTriggered();
            }
        });

        JButton cardsButton = new JButton("Cards");
        cardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cardTriggered();
            }
        });

        JButton atmsButton = new JButton("ATMs near me");
        atmsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.atmMapTriggered();
            }
        });

        JButton assetsButton = new JButton("Assets");
        assetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buyAssetsTriggered();
            }
        });

        JButton loansButton = new JButton("Apply Loans");
        loansButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { controller.applyLoansTriggered(); }
        });

        JButton loansHistoryButton = new JButton("Loans History");
        loansHistoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { controller.seeLoansHistoryTriggered(); }
        });

        JButton housesButton = new JButton("Houses near me");
        housesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.houseMapTriggered();
            }
        });

        JButton exchangeButton = new JButton("Currency exchange");
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exchangeTriggered();
            }
        });

        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(cardsButton);
        buttonPanel.add(atmsButton);
        buttonPanel.add(housesButton);
        buttonPanel.add(assetsButton);
        buttonPanel.add(loansButton);
        buttonPanel.add(loansHistoryButton);
        buttonPanel.add(exchangeButton);

        add(buttonPanel, BorderLayout.CENTER);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logOutTriggered();
            }
        });
    }
}
