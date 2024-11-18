package Views;

import javax.swing.*;
import java.awt.*;
import LogInController.LogInController;

public class LogInView extends JFrame {

    public LogInView() {
        setTitle("Log In");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(5, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel userIDLabel = new JLabel("User ID:");
        JTextField userIDField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton logInButton = new JButton("Log In");

        loginPanel.add(firstNameLabel);
        loginPanel.add(firstNameField);
        loginPanel.add(lastNameLabel);
        loginPanel.add(lastNameField);
        loginPanel.add(userIDLabel);
        loginPanel.add(userIDField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(logInButton);

        add(loginPanel);

        logInButton.addActionListener(e -> {
            int userID = Integer.parseInt(userIDField.getText());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = new String(passwordField.getPassword());

            LogInController logInController = new LogInController();

            logInController.login(userID, firstName, lastName, password);

            if (logInController.getLoggedUser() != null) {
                new LoggedInView(logInController.getLoggedUser()).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username, first name, last name, or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
