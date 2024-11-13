package Exchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyExchange extends JFrame {
    private JTextField inputAmountField;
    private JTextField outputAmountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JButton exchangeButton;
    private JButton backButton;
    public static double rate;

    public CurrencyExchange() {
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

        String[] currencies = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
                "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
                "BSD", "BTN", "BWP", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNH",
                "CNY", "COP", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP",
                "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS", "GIP", "GMD",
                "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "ICP", "IDR",
                "ILS", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES",
                "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP",
                "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT",
                "MOP", "MRO", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD",
                "NGN", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR",
                "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RUR", "RWF", "SAR", "SBD",
                "SCR", "SDG", "SDR", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "SYP",
                "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS",
                "UAH", "UGX", "USD", "UYU", "UZS", "VND", "VUV", "WST", "XAF", "XCD",
                "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL"};
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

        // 输出金额
        JLabel outputLabel = new JLabel("Output Amount:");
        outputLabel.setBounds(50, 200, 100, 25);
        add(outputLabel);

        outputAmountField = new JTextField();
        outputAmountField.setBounds(150, 200, 150, 25);
        outputAmountField.setEditable(false);
        add(outputAmountField);

        // 兑换按钮
        exchangeButton = new JButton("Exchange Currency");
        exchangeButton.setBounds(50, 250, 200, 30);
        add(exchangeButton);
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exchangeCurrency();
            }
        });

        // 返回按钮
        backButton = new JButton("BACK TO MAIN");
        backButton.setBounds(400, 300, 150, 30);
        add(backButton);
        backButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // 模拟货币兑换的简单逻辑
    private void exchangeCurrency() {
        try {
            double inputAmount = Double.parseDouble(inputAmountField.getText());
            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();

            // Use Useage to change the rate
            // double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
            UseageExchange.changeInto(fromCurrency, toCurrency);
            double outputAmount = inputAmount * rate;
            outputAmountField.setText(String.format("%.2f", outputAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input Amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 获取汇率（这里使用固定汇率作为示例）
//    private double getExchangeRate(String from, String to) {
//        if (from.equals(to)) return 1.0;
//        if (from.equals("USD") && to.equals("EUR")) return 0.9;
//        if (from.equals("EUR") && to.equals("USD")) return 1.1;
//        if (from.equals("USD") && to.equals("CNY")) return 7.1;
//        if (from.equals("CNY") && to.equals("USD")) return 0.14;
//        return 1.0; // 默认汇率
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyExchange::new);
    }
}
