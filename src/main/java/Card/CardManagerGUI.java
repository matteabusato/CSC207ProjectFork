package Card;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardManagerGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField idField, nameField, expiryDateField, securityCodeField;

    public CardManagerGUI() {
        // Main frame
        frame = new JFrame("Card Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table of form
        String[] columns = {"ID", "Name", "Expiry Date", "Security Code"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // input frame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Expiry Date:"));
        expiryDateField = new JTextField();
        inputPanel.add(expiryDateField);

        inputPanel.add(new JLabel("Security Code:"));
        securityCodeField = new JTextField();
        inputPanel.add(securityCodeField);

        // 添加按钮
        JButton addButton = new JButton("Add Card");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCard();
            }
        });

        inputPanel.add(addButton);

        // 将输入面板和表格添加到主框架
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // 添加卡片到表格
    private void addCard() {
        String id = idField.getText();
        String name = nameField.getText();
        String expiryDate = expiryDateField.getText();
        String securityCode = securityCodeField.getText();
        // 加入新的判断，例如不能出现同样id
        if (id.isEmpty() || name.isEmpty() || expiryDate.isEmpty() || securityCode.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.addRow(new Object[]{id, name, expiryDate, securityCode});

            // 清空输入字段
            idField.setText("");
            nameField.setText("");
            expiryDateField.setText("");
            securityCodeField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CardManagerGUI());
    }
}
