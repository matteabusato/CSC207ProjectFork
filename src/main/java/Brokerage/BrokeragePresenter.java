package Brokerage;

import javax.swing.*;
import java.awt.*;


public class BrokeragePresenter extends JFrame {

    private final JTextField stockNameField;
    private final JLabel stockPriceLabel;
    private final JButton buyButton, sellButton, searchButton;
    private final JPanel graphPanel;
    private final JFrame frame;


    public BrokeragePresenter() {
        // Initialize Swing UI
        frame = new JFrame("Crazy Bank - Asset Managing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        //ImageIcon customIcon = new ImageIcon("src/logo.png");

        // Create Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Use FlowLayout for horizontal layout

        // Components for input
        JLabel nameLabel = new JLabel("Stock Symbol:");
        stockNameField = new JTextField(10); // Set preferred width for the text field
        JLabel priceLabel = new JLabel("Price $:");
        stockPriceLabel = new JLabel("N/A");

        // Search Button
        searchButton = new JButton("Search");

        // Add components to the input panel
        inputPanel.add(nameLabel);
        inputPanel.add(stockNameField);
        inputPanel.add(searchButton);
        inputPanel.add(priceLabel);
        inputPanel.add(stockPriceLabel);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buyButton = new JButton("Buy Asset");
        sellButton = new JButton("Sell Asset");
        buyButton.setEnabled(false);
        sellButton.setEnabled(false);

        buttonsPanel.add(buyButton);
        buttonsPanel.add(sellButton);

        // Graph Panel
        graphPanel = new JPanel(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createTitledBorder("Stock Price Trends"));

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(graphPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void show() {
        frame.setVisible(true);
    }

    public JTextField getStockNameField() {
        return stockNameField;
    }

    public JLabel getStockPriceLabel() {
        return stockPriceLabel;
    }

    public JButton getBuyButton() {
        return buyButton;
    }

    public JButton getSellButton() {
        return sellButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JPanel getGraphPanel() {
        return graphPanel;
    }
}
