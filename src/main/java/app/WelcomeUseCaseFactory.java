package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeUseCaseFactory {
    final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
    final JLabel titleLabel = new JLabel("Welcome to Crazy Bank!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
    final JButton loginButton = new JButton("Log In");
    final JButton signUpButton = new JButton("Sign Up");
        loginButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        signUpButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

    add(mainPanel);

        loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.logInTriggered();
        }
    });

        signUpButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.signUpTriggered();
        }
    });

}
