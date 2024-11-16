package Brokerage;


import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;

import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class Brokerage {

    private static JTextField stockNameField;
    private static JLabel stockPriceLabel;
    private static JButton buyButton, sellButton;
    private static JPanel graphPanel;

    static ImageIcon customIcon = new ImageIcon("src/logo.png");

    public static void main(String[] args) {

        // Initialize Alpha Vantage API
        Config cfg = Config.builder()
                .key("OTC0BTKHGEJBYCL7") // Security issue
                .timeOut(10)
                .build();
        AlphaVantage.api()
                .init(cfg);

        // Create Swing UI
        JFrame frame = new JFrame("Crazy Bank - Asset Managing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Use FlowLayout for horizontal layout


        // Components for input
        JLabel nameLabel = new JLabel("Stock Symbol:");
        stockNameField = new JTextField(10);  // Set preferred width for the text field
        JLabel priceLabel = new JLabel("Price $:");
        stockPriceLabel = new JLabel("N/A");

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchStock());

        // Add components to the input panel
        inputPanel.add(nameLabel);
        inputPanel.add(stockNameField);
        inputPanel.add(searchButton);  // Add search button next to the text field
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


        frame.setVisible(true);
    }

    private static void searchStock() {
        String stockSymbol = stockNameField.getText().trim();

        if (stockSymbol.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol("AAPL")
                .interval(Interval.THIRTY_MIN)
                .outputSize(OutputSize.FULL)
                .dataType(DataType.JSON)
                .fetchSync();

        // Fetch stock data from Alpha Vantage
        List<StockUnit> stockUnits = response.getStockUnits();
        System.out.println(stockUnits);
        System.out.println(response);


        if (stockUnits != null && !stockUnits.isEmpty()) {
            // Display the latest price
            double latestPrice = stockUnits.get(0).getClose();
            stockPriceLabel.setText(String.format("$%.2f", latestPrice));
            buyButton.setEnabled(true);
            buyButton.addActionListener(e -> buyAction(latestPrice, stockSymbol));
            sellButton.setEnabled(true);
            sellButton.addActionListener(e -> sellAction(latestPrice, stockSymbol));

            // Draw the price graph
            drawGraph(stockSymbol, stockUnits);
        } else {
            stockPriceLabel.setText("N/A");
            JOptionPane.showMessageDialog(null, "No data found for this stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Action Listener for Buy Button (using custom dialog)
    public static void buyAction(double latestPrice, String stockSymbol) {
        // Spinner with min 1, max Integer.MAX_VALUE, step size 1
        JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        JLabel l = new JLabel("Total Bid Price: " + latestPrice);
        amountSpinner.addChangeListener(e -> l.setText(String.format("Total Bid Price: %.2f", (Integer) amountSpinner.getValue() * latestPrice)));

        final JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Enter amount of security to buy:"));
        panel.add(amountSpinner);
        panel.add(l);

        int option = JOptionPane.showConfirmDialog(
                null,  // Parent component
                panel,  // The panel with the spinner
                "Buy Asset",  // Dialog title
                JOptionPane.OK_CANCEL_OPTION,  // Options: OK and Cancel buttons
                JOptionPane.PLAIN_MESSAGE,  // Message type
                customIcon
        );

        // If the user clicks OK
        if (option == JOptionPane.OK_OPTION) {
            int amount = (int) amountSpinner.getValue();
            if (amount > 0) {
                JOptionPane.showMessageDialog(
                        null,  // Parent component
                        "You have bought " + amount + " shares of " + stockSymbol,  // Message to display
                        "Success",  // Title
                        JOptionPane.INFORMATION_MESSAGE  // Message type
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid amount greater than zero.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private static void sellAction(double latestPrice, String stockSymbol) {
        int amountOwn = 10;

        // Spinner with min 1, max Integer.MAX_VALUE, step size 1
        JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, amountOwn, 1));
        JLabel l = new JLabel("Total Ask Price: " + latestPrice);
        amountSpinner.addChangeListener(e -> l.setText(String.format("Total Ask Price: %.2f", (Integer) amountSpinner.getValue() * latestPrice)));

        final JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Enter amount of security to sell:"));
        panel.add(amountSpinner);
        panel.add(l);

        int option = JOptionPane.showConfirmDialog(
                null,  // Parent component
                panel,  // The panel with the spinner
                "Sell Asset",  // Dialog title
                JOptionPane.OK_CANCEL_OPTION,  // Options: OK and Cancel buttons
                JOptionPane.PLAIN_MESSAGE,  // Message type
                customIcon
        );

        // If the user clicks OK
        if (option == JOptionPane.OK_OPTION) {
            int amount = (int) amountSpinner.getValue();
            if (amount > 0) {
                JOptionPane.showMessageDialog(
                        null,  // Parent component
                        "You have sold " + amount + " shares of " + stockSymbol,  // Message to display
                        "Success",  // Title
                        JOptionPane.INFORMATION_MESSAGE  // Message type
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid amount greater than zero.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    private static void drawGraph(String stockSymbol, List<StockUnit> stockUnits) {
        // Create a time series for stock prices
        TimeSeries series = new TimeSeries(stockSymbol);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Adjust the format if necessary

        for (StockUnit unit : stockUnits) {
            try {
                // Parse the time string into a Date object
                java.util.Date date = dateFormat.parse(unit.getDate());

                // Add the time and close price to the time series
                series.addOrUpdate(new Second(date), unit.getClose());  // 'Second' class uses a Date
            } catch (Exception e) {
                e.printStackTrace(); // Handle invalid date format or parsing issues
            }
        }

        // Create a dataset
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        // Create a line chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Stock Price Trends - " + stockSymbol, // Title
                "Time",                                // X-axis label
                "Price ($)",                           // Y-axis label
                dataset,                               // Dataset
                false,                                 // Legend
                true,                                  // Tooltips
                false                                  // URLs
        );

        // Get the plot from the chart
        XYPlot plot = (XYPlot) chart.getPlot();

        // Set the color of the first series (series 0)
        plot.getRenderer().setSeriesPaint(0, Color.decode("#222d64")); // Change to desired color

        // Optional: Set line thickness for better visibility
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f)); // Make the line thicker

        // Create a chart panel and replace the graph panel
        ChartPanel chartPanel = new ChartPanel(chart);
        graphPanel.removeAll();
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        graphPanel.validate();


    }
}