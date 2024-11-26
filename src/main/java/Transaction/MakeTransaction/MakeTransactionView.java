package Transaction.MakeTransaction;

import UserDataObject.UserObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeTransactionView extends JFrame {
    private final JLabel messageLabel = new JLabel();
    UserObject user;

    public MakeTransactionView(MakeTransactionController controller) {
        this.user = controller.loggedInUser;

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

        JLabel cardLabel = new JLabel("Card:");
        String[] cardOptions = {"1", "2", "3"};
        JComboBox<String> cardDropdown = new JComboBox<>(cardOptions);

        JLabel receiverIDLabel = new JLabel("Receiver UserID:");
        JTextField receiverIDField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JButton processButton = new JButton("Process Transaction");

        transactionPanel.add(cardLabel);
        transactionPanel.add(cardDropdown);
        transactionPanel.add(receiverIDLabel);
        transactionPanel.add(receiverIDField);
        transactionPanel.add(amountLabel);
        transactionPanel.add(amountField);
        transactionPanel.add(new JLabel());
        transactionPanel.add(processButton);

        add(transactionPanel, BorderLayout.CENTER);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedCard = (String) cardDropdown.getSelectedItem();
                    int receiverID = Integer.parseInt(receiverIDField.getText());
                    int amount = Integer.parseInt(amountField.getText());

                    boolean success = controller.makeTransactionTriggered(selectedCard, receiverID, amount);
                    if (success) {
                        controller.onMakeTransactonSuccess(selectedCard, receiverID, amount);
                    } else {
                        displayMessage("Please fill all the fields.", false);
                    }
                } catch (NumberFormatException ex) {
                    displayMessage("UserID and Amount must be numbers.", false);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logOutTriggered();
            }
        });
    }

    public void displayMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        messageLabel.setForeground(isSuccess ? Color.GREEN : Color.RED);
    }
}
