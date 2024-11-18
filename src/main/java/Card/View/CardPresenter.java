package Card.View;

import Card.Method.CardController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardPresenter {
    public JFrame frame;
    public JTable table;
    public DefaultTableModel model;
    public JTextField nameField;

    public CardPresenter() {
        // Main frame
        frame = new JFrame("Card Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table of form

        String[] columns = {"ID", "Name", "Expiry Date", "Security Code", "Amount"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // input frame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        //Button
        JButton addButton = new JButton("Add Card");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardController.addCard(CardPresenter.this);
            }
        });

        JButton deleteButton = new JButton("Delete Card");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardController.deleteCard(CardPresenter.this);
            }
        });

        JButton backButton = new JButton("BACK TO MAIN");
        backButton.addActionListener(e -> System.exit(0));

        JPanel theButton = new JPanel();
        theButton.setLayout(new GridLayout(1,3));

        theButton.add(addButton);
        theButton.add(deleteButton);
        theButton.add(backButton);

        inputPanel.add(theButton);

        // final frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        CardController.refresh(CardPresenter.this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CardPresenter());
    }
}
