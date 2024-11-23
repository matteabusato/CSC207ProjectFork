package Card;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardView extends JFrame{
    public JFrame frame;
    public JTable table;
    public DefaultTableModel model;
    public JTextField usageField;

    public CardView(CardController controller) {
        // Main frame
        frame = new JFrame("Card Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table of form

        String[] columns = {"ID", "Usage", "Expiry Date", "Security Code", "Expenses"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // input frame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        usageField = new JTextField();
        inputPanel.add(usageField);

        //Button
        JButton addButton = new JButton("Add Card");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCard();
            }
        });

        JButton deleteButton = new JButton("Delete Card");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCard();
            }
        });

        JButton backButton = new JButton("BACK TO MAIN");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });

        JPanel theButton = new JPanel();
        theButton.setLayout(new GridLayout(1, 3));

        theButton.add(addButton);
        theButton.add(deleteButton);
        theButton.add(backButton);

        inputPanel.add(theButton);

        // final frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * used to renew the GUI as a help function to add/delete card
     * @param cardView the GUI need to renew
     */
    public static void refresh(CardView cardView) {
        CardController.loadFromFile();
        cardView.model.setRowCount(0);
        for (Card card : CardController.cardList) {
            cardView.model.addRow
                    (new Object[]{card.getId(), card.getUsage(), card.getDate(), card.getCode(),card.getExpenses() + "$"});
        }
    }

    /**
     * used to add the card and renew the GUI
     */
    public void addCard() {
        CardController.loadFromFile();
        String name = usageField.getText();
        String securityCode = CardController.newCode();
        String id = CardController.newId(name);
        String expiryDate = CardController.newDate();

        if (CardController.cardList.size() >= 10) {
            JOptionPane.showMessageDialog
                    (frame, "Too much cards", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Card newCard = new Card(id, name, expiryDate, securityCode);
            model.addRow(new Object[]{id, name, expiryDate, securityCode, newCard.getExpenses()});
            CardController.saveCards(newCard);
            usageField.setText("");
        }
        refresh(this);
    }

    /**
     * used to delete the card and renew the GUI
     */
    public void deleteCard() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == - 1) {
            JOptionPane.showMessageDialog(frame, "Please select a row");
        }
        else {
            CardController.cardList.remove(selectedRow);
            CardController.saveDeleteCard(selectedRow);
        }
        refresh(this);
    }
}