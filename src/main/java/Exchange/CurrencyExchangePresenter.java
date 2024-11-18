package Exchange;

import javax.swing.*;
import java.awt.event.*;

public class CurrencyExchangePresenter extends JFrame {
    private JTextField inputAmountField;
    public static JTextField outputAmountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JButton exchangeButton;
    private JButton backButton;

    public CurrencyExchangePresenter() {
        setTitle("Currency Exchange");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // input amount
        JLabel inputLabel = new JLabel("Input Amount:");
        inputLabel.setBounds(50, 50, 100, 25);
        add(inputLabel);

        inputAmountField = new JTextField();
        inputAmountField.setBounds(150, 50, 150, 25);
        add(inputAmountField);

        // input currency
        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setBounds(50, 100, 100, 25);
        add(fromLabel);

        String[] currencies = {"AMD", "ANG", "AOA", "AUD", "AWG", "AZN", "BAM", "BDT", "BGN", "BHD",
                "BIF", "BMD", "BND", "BOB", "BSD", "BTN", "BWP", "BZD", "CAD", "CDF", "CHF", "CLF",
                "CLP", "CNH", "CNY", "COP", "DOP", "EGP", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS",
                "GIP", "GMD", "GNF", "GYD", "HKD", "HNL", "HRK", "HTG", "ICP", "IDR", "ILS", "INR",
                "ISK", "JEP", "JPY", "KES", "KHR", "KMF", "KRW", "KWD", "KYD", "LAK", "LBP", "LKR",
                "MAD", "MDL", "MMK", "MOP", "MRU", "MWK", "MYR", "NGN", "NPR", "NZD", "OMR", "PAB",
                "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RUR", "RWF", "SAR",
                "SBD", "SDR", "SGD", "SHP", "SRD", "SYP", "SZL", "TMT", "TND", "TOP", "TRY", "TWD",
                "UGX", "USD", "UYU", "VUV", "WST", "XAF", "XDR", "XOF", "XPF", "ZWL"};
        fromCurrencyBox = new JComboBox<>(currencies);
        fromCurrencyBox.setBounds(150, 100, 150, 25);
        add(fromCurrencyBox);

        // aim currency
        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setBounds(50, 150, 100, 25);
        add(toLabel);

        toCurrencyBox = new JComboBox<>(currencies);
        toCurrencyBox.setBounds(150, 150, 150, 25);
        add(toCurrencyBox);

        // output Amount
        JLabel outputLabel = new JLabel("Output Amount:");
        outputLabel.setBounds(50, 200, 100, 25);
        add(outputLabel);

        outputAmountField = new JTextField();
        outputAmountField.setBounds(150, 200, 150, 25);
        outputAmountField.setEditable(false);
        add(outputAmountField);

        // exchange button
        exchangeButton = new JButton("Exchange Currency");
        exchangeButton.setBounds(50, 250, 200, 30);
        add(exchangeButton);
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrencyExchangeController.exchangeCurrency(
                        CurrencyExchangePresenter.this,
                        fromCurrencyBox,
                        inputAmountField,
                        outputAmountField,
                        toCurrencyBox);
            }
        });

        // back button
        backButton = new JButton("BACK TO MAIN");
        backButton.setBounds(400, 300, 150, 30);
        add(backButton);
        backButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyExchangePresenter::new);
    }
}
