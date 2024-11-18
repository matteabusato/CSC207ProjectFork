package Views;

import DataObjects.User;

import javax.swing.*;
import java.awt.*;

public class SignUpPresenter extends JFrame {

    private JTextField firstNameField, lastNameField;
    private JPasswordField passwordField;

    public SignUpPresenter() {
        setTitle("Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(4, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton signUpButton = new JButton("Sign Up");

        signUpPanel.add(firstNameLabel);
        signUpPanel.add(firstNameField);
        signUpPanel.add(lastNameLabel);
        signUpPanel.add(lastNameField);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(passwordField);
        signUpPanel.add(new JLabel());
        signUpPanel.add(signUpButton);

        add(signUpPanel);

        signUpButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();

            if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                SignUpController signUpController = new SignUpController();
                // TODO: User user = signUpController.signUp(firstName, lastName, password)

                //new LoggedInView(user).setVisible(true);
                dispose();
            }
        });
    }
}
