import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LoanMortgage {
    private static double loanAmount = 0;
    private static double mortgageAmount = 0;
    private static double loanInterestRate = 0.05; // 5% interest rate
    private static double mortgageInterestRate = 0.03; // 3% interest rate
    private static JLabel loanBalanceLabel;
    private static JLabel mortgageBalanceLabel;
    private static JLabel dateLabel;
    private static final DecimalFormat df = new DecimalFormat("#.##");
    private static final Calendar calendar = Calendar.getInstance();

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Loan and Mortgage Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new GridLayout(6, 1));

        // Panel for Date Display
        JPanel datePanel = new JPanel();
        datePanel.setBorder(BorderFactory.createTitledBorder("Current Date"));
        dateLabel = new JLabel();

        // Combo boxes for adjusting year, month, and day
        Integer[] years = new Integer[201];
        for (int i = 1900; i <= 2100; i++) {
            years[i - 1900] = i;
        }
        JComboBox<Integer> yearComboBox = new JComboBox<>(years);
        yearComboBox.setSelectedItem(calendar.get(Calendar.YEAR));

        Integer[] months = new Integer[12];
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = i;
        }
        JComboBox<Integer> monthComboBox = new JComboBox<>(months);
        monthComboBox.setSelectedItem(calendar.get(Calendar.MONTH) + 1);

        updateDayComboBoxItems();
        JComboBox<Integer> dayComboBox = new JComboBox<>(updateDayComboBoxItems());
        dayComboBox.setSelectedItem(calendar.get(Calendar.DAY_OF_MONTH));

        datePanel.add(new JLabel("Year:"));
        datePanel.add(yearComboBox);
        datePanel.add(new JLabel("Month:"));
        datePanel.add(monthComboBox);
        datePanel.add(new JLabel("Day:"));
        datePanel.add(dayComboBox);
        // Removed the date label from the UI as requested
        updateDateLabel();

        yearComboBox.addActionListener(e -> {
            calendar.set(Calendar.YEAR, (Integer) yearComboBox.getSelectedItem());
            updateDateLabel();
        });

        monthComboBox.addActionListener(e -> {
            calendar.set(Calendar.MONTH, (Integer) monthComboBox.getSelectedItem() - 1);
            updateDateLabel();
        });

        dayComboBox.addActionListener(e -> {
            Integer selectedDay = (Integer) dayComboBox.getSelectedItem();
            if (selectedDay != null) {
                calendar.set(Calendar.DAY_OF_MONTH, selectedDay);
                updateDateLabel();
            }
        });

        // Panel for Loan Management
        JPanel loanPanel = new JPanel();
        loanPanel.setBorder(BorderFactory.createTitledBorder("Loan Management"));
        loanPanel.setLayout(new GridLayout(4, 2));

        JTextField loanAmountField = new JTextField();
        JButton requestLoanButton = new JButton("Request Loan");
        loanBalanceLabel = new JLabel("Current Loan Balance: $0");

        loanPanel.add(new JLabel("Loan Amount:"));
        loanPanel.add(loanAmountField);
        loanPanel.add(requestLoanButton);
        loanPanel.add(loanBalanceLabel);

        requestLoanButton.addActionListener(e -> {
            try {
                loanAmount += Double.parseDouble(loanAmountField.getText());
                loanBalanceLabel.setText("Current Loan Balance: $" + df.format(loanAmount));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
            }
        });

        // Panel for Mortgage Management
        JPanel mortgagePanel = new JPanel();
        mortgagePanel.setBorder(BorderFactory.createTitledBorder("Mortgage Management"));
        mortgagePanel.setLayout(new GridLayout(5, 2));

        JTextField mortgageAmountField = new JTextField();
        JTextField downPaymentField = new JTextField();
        JButton requestMortgageButton = new JButton("Request Mortgage");
        mortgageBalanceLabel = new JLabel("Current Mortgage Balance: $0");

        mortgagePanel.add(new JLabel("Mortgage Amount:"));
        mortgagePanel.add(mortgageAmountField);
        mortgagePanel.add(new JLabel("Down Payment (%):"));
        mortgagePanel.add(downPaymentField);
        mortgagePanel.add(requestMortgageButton);
        mortgagePanel.add(mortgageBalanceLabel);

        requestMortgageButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(mortgageAmountField.getText());
                double downPaymentPercent = Double.parseDouble(downPaymentField.getText());
                double downPayment = (downPaymentPercent / 100) * amount;
                mortgageAmount += (amount - downPayment);
                mortgageBalanceLabel.setText("Current Mortgage Balance: $" + df.format(mortgageAmount));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid amounts.");
            }
        });

        // Panel for Interest Rate Adjustment
        JPanel interestRatePanel = new JPanel();
        interestRatePanel.setBorder(BorderFactory.createTitledBorder("Interest Rates Adjustment"));
        interestRatePanel.setLayout(new GridLayout(4, 2));

        JTextField loanInterestRateField = new JTextField(String.valueOf(loanInterestRate * 100));
        JTextField mortgageInterestRateField = new JTextField(String.valueOf(mortgageInterestRate * 100));
        JButton updateInterestRatesButton = new JButton("Update Interest Rates");

        interestRatePanel.add(new JLabel("Loan Interest Rate (%):"));
        interestRatePanel.add(loanInterestRateField);
        interestRatePanel.add(new JLabel("Mortgage Interest Rate (%):"));
        interestRatePanel.add(mortgageInterestRateField);
        interestRatePanel.add(updateInterestRatesButton);

        updateInterestRatesButton.addActionListener(e -> {
            try {
                loanInterestRate = Double.parseDouble(loanInterestRateField.getText()) / 100;
                mortgageInterestRate = Double.parseDouble(mortgageInterestRateField.getText()) / 100;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid interest rates.");
            }
        });

        // Panel for Automatic Payment Options
        JPanel paymentPanel = new JPanel();
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Automatic Payment Options"));
        paymentPanel.setLayout(new GridLayout(3, 1));

        JCheckBox autoLoanPaymentCheckbox = new JCheckBox("Enable Automatic Loan Payments");
        JCheckBox autoMortgagePaymentCheckbox = new JCheckBox("Enable Automatic Mortgage Payments");

        paymentPanel.add(autoLoanPaymentCheckbox);
        paymentPanel.add(autoMortgagePaymentCheckbox);

        // Add panels to frame
        frame.add(datePanel);
        frame.add(loanPanel);
        frame.add(mortgagePanel);
        frame.add(interestRatePanel);
        frame.add(paymentPanel);

        // Timer to calculate interest in the background
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    // Calculate loan interest
                    loanAmount += loanAmount * (loanInterestRate / 100 / 365 / 24 / 60 / 60);
                    loanBalanceLabel.setText("Current Loan Balance: $" + df.format(loanAmount));

                    // Calculate mortgage interest
                    mortgageAmount += mortgageAmount * (mortgageInterestRate / 100 / 365 / 24 / 60 / 60);
                    mortgageBalanceLabel.setText("Current Mortgage Balance: $" + df.format(mortgageAmount));

                    // Update date label
                    updateDateLabel();
                });
            }
        }, 1000, 1000); // Update every second

        // Show frame
        frame.setVisible(true);
    }

    private static void updateDateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateLabel.setText("Current Date: " + sdf.format(calendar.getTime()));
    }

    private static void updateDayComboBox(JComboBox<Integer> dayComboBox) {
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayComboBox.removeAllItems();
        for (int i = 1; i <= maxDays; i++) {
            dayComboBox.addItem(i);
        }
        // Ensure the selected day is within the valid range
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (currentDay > maxDays) {
            currentDay = maxDays;
            calendar.set(Calendar.DAY_OF_MONTH, currentDay);
        }
        dayComboBox.setSelectedItem(currentDay);
    }

    private static Integer[] updateDayComboBoxItems() {
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Integer[] days = new Integer[maxDays];
        for (int i = 1; i <= maxDays; i++) {
            days[i - 1] = i;
        }
        return days;
    }
}
