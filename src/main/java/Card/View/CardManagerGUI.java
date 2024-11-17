package Card.View;

import Card.Method.CardMethod;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardManagerGUI {
    public JFrame frame;
    public JTable table;
    public DefaultTableModel model;
    public JTextField idField;
    public JTextField nameField;
    public JTextField expiryDateField;
    public JTextField securityCodeField;

    public CardManagerGUI() {
        // Main frame
        frame = new JFrame("Card Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table of form

        String[] columns = {"ID", "Name", "Expiry Date", "Security Code", "Amount"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // input frame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        idField.setEnabled(false);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Expiry Date:"));
        expiryDateField = new JTextField();
        expiryDateField.setEnabled(false);
        inputPanel.add(expiryDateField);

        inputPanel.add(new JLabel("Security Code:"));
        securityCodeField = new JTextField();
        securityCodeField.setEnabled(false);
        inputPanel.add(securityCodeField);

        // Button
        JButton addButton = new JButton("Add Card");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardMethod.addCard(CardManagerGUI.this);
            }
        });

        JButton deleteButton = new JButton("Delete Card");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCard();
            }
        });

        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // 将输入面板和表格添加到主框架
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void deleteCard() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CardManagerGUI());
    }
}
