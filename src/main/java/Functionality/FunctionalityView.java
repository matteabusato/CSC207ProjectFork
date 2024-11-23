package Functionality;

import LogIn.LoggedIn.LoggedInController;
import Views.ButtonMaker;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FunctionalityView extends JFrame {


    public FunctionalityView(JPanel panel, LoggedInController controller) {
        super();
        pack();
        Insets insets = getInsets();
        int frameWidth = panel.getWidth()+ insets.left + insets.right;
        int frameHeight = panel.getHeight() + insets.top + insets.bottom;
        setSize(frameWidth, frameHeight + 100); // Set the size of the frame
        add(panel);

        JPanel buttonPanel = new PanelMaker(0, 600, 600, 100, null);
        JButton backButton = new ButtonMaker("Back", 250, 625, 100, 50) {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.launch();
            }
        };
        buttonPanel.add(backButton);
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Application on clicking X
        setLocationRelativeTo(null); //Puts the Menu in the center
    }
}
