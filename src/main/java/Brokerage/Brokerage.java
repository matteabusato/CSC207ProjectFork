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

    public static void main(String[] args) {
        // Initialize Alpha Vantage API

        Config cfg = Config.builder()
                .key("QD1VO3QMDYPFCTIB") //security issue
                .timeOut(10)
                .build();
        AlphaVantage.api()
                .init(cfg); // Replace with your Alpha Vantage API key

        // Create Swing UI
        JFrame frame = new JFrame("Brokerage App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create Input Panel with Search Button next to the text field
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
        if (stockUnits != null && !stockUnits.isEmpty()) {
            // Display the latest price
            double latestPrice = stockUnits.get(0).getClose();
            stockPriceLabel.setText(String.format("$%.2f", latestPrice));
            buyButton.setEnabled(true);
            sellButton.setEnabled(true);

            // Draw the price graph
            drawGraph(stockSymbol, stockUnits);
        } else {
            stockPriceLabel.setText("N/A");
            JOptionPane.showMessageDialog(null, "No data found for this stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
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

        // Create a chart panel and replace the graph panel
        ChartPanel chartPanel = new ChartPanel(chart);
        graphPanel.removeAll();
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        graphPanel.validate();
    }
}