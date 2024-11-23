package Loan;

import Loan.LoanRequest.LoanRequestController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

public class LoanView extends JFrame {
    private final JLabel loanBalanceLabel;
    private final DecimalFormat df = new DecimalFormat("#.##");

    public LoanView(LoanController controller) {
        // Frame setup
        setTitle("Loan");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main input panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input fields
        JTextField loanAmountField = new JTextField(15);
        JTextField loanTermField = new JTextField(15);
        JTextField loanInterestRateField = new JTextField("5.0", 15);

        // Labels
        JLabel loanAmountLabel = new JLabel("Amount ($):");
        JLabel loanTermLabel = new JLabel("Term (Yrs):");
        JLabel loanInterestRateLabel = new JLabel("Interest Rate (%):");

        // Add input fields and labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(loanAmountLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(loanAmountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(loanTermLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(loanTermField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(loanInterestRateLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(loanInterestRateField, gbc);

        // Card selection panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.insets = new Insets(10, 10, 10, 10);
        cardGbc.fill = GridBagConstraints.HORIZONTAL;

        // Button 1: "Choose CARD to Get Loan"
        JButton getLoanButton = new JButton("Choose CARD to Get Loan");
        getLoanButton.setPreferredSize(new Dimension(200, 30));

        // Table 1: For Loan (only column headers)
        String[] columns = {"ID", "Name", "Expiry Date", "Security Code", "Amount"};
        DefaultTableModel table1Model = new DefaultTableModel(columns, 0);
        JTable table1 = new JTable(table1Model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setRowSelectionAllowed(false);
        table1.setFocusable(false);
        JScrollPane table1ScrollPane = new JScrollPane(table1);
        table1ScrollPane.setPreferredSize(new Dimension(500, 40));

        // Button and table stacked vertically
        cardGbc.gridx = 0;
        cardGbc.gridy = 0;
        cardGbc.gridwidth = 2;
        cardPanel.add(getLoanButton, cardGbc);

        cardGbc.gridy = 1;
        cardPanel.add(table1ScrollPane, cardGbc);

        // Button 2: "Choose CARD for Loan Repayment"
        JButton repaymentButton = new JButton("Choose CARD for Loan Repayment");
        repaymentButton.setPreferredSize(new Dimension(200, 30));

        // Table 2: For Repayment (only column headers)
        DefaultTableModel table2Model = new DefaultTableModel(columns, 0);
        JTable table2 = new JTable(table2Model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table2.setRowSelectionAllowed(false);
        table2.setFocusable(false);
        JScrollPane table2ScrollPane = new JScrollPane(table2);
        table2ScrollPane.setPreferredSize(new Dimension(500, 40));

        // Button and table stacked vertically
        cardGbc.gridy = 2;
        cardPanel.add(repaymentButton, cardGbc);

        cardGbc.gridy = 3;
        cardPanel.add(table2ScrollPane, cardGbc);

        // Bottom buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton requestLoanButton = new JButton("Request Loan");
        JButton loanHistoryButton = new JButton("Loan History");
        JButton backToMainButton = new JButton("Back To Main");

        loanBalanceLabel = new JLabel("Current Loan Balance: $0", SwingConstants.CENTER);

        buttonPanel.add(requestLoanButton);
        buttonPanel.add(loanHistoryButton);
        buttonPanel.add(backToMainButton);

        // Modify Request Loan button behavior
        requestLoanButton.addActionListener(e -> {
            LoanRequestController loanRequestController = new LoanRequestController(
                    loanAmountField, loanTermField, loanInterestRateField
            );
            loanRequestController.showLoanRequestPopup();
        });

        // Add everything to the frame
        add(mainPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateLoanBalanceLabel(double balance) {
        loanBalanceLabel.setText("Current Loan Balance: $" + df.format(balance));
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
