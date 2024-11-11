package Launcher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame {
    private double balance = 1000.00; // Initial balance
    private final JLabel balanceLabel;

    public Launcher() {
        setTitle("Banking App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Balance Display
        balanceLabel = new JLabel("Balance: $" + balance);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

        // Buttons
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton viewTransactionsButton = new JButton("View Transactions");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");

        // Add buttons to panel
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(viewTransactionsButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        // Add panel to frame
        add(buttonPanel, BorderLayout.CENTER);

        // Button Actions
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to deposit:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        balance += amount;
                        updateBalanceLabel();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input");
                    }
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        if (amount <= balance) {
                            balance -= amount;
                            updateBalanceLabel();
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient balance");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input");
                    }
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Transfer feature coming soon!");
            }
        });

        viewTransactionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "View Transactions feature coming soon!");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                balance = 1000.00; // Reset balance to initial value
                updateBalanceLabel();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Launcher().setVisible(true);
        });
    }
}

