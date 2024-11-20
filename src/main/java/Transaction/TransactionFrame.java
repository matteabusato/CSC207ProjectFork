package main.java.Transaction;

import main.java.Views.FrameMaker;
import main.java.Views.LabelMaker;
import main.java.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;

public class TransactionFrame extends FrameMaker {

    public TransactionFrame() {
        super("Transactions", 600, 600);

        JPanel title = new PanelMaker(0, 0, 600, 100, new FlowLayout());
        JLabel label = new LabelMaker("Balance", new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        title.add(label);
        this.add(title);

        JPanel panel = new PanelMaker( 0, 120, 300, 300,null);
        this.add(panel);
        JLabel l = new LabelMaker("Transactions", null, 0, 0, 50, 20);
        panel.add(l);

        JButton a = new TransactionButton("Transaction", Color.blue, Color.green, 50, 0, 10, 50);
        JButton b = new TransactionButton("Transaction2", Color.blue, Color.green, 90, 50,50, 10);
        panel.add(a);
        panel.add(b);

    }
}