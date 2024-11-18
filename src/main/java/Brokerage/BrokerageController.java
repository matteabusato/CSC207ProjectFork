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



public class BrokerageController {

    private final BrokeragePresenter view;
    static ImageIcon customIcon = new ImageIcon("src/logo.png");

    public BrokerageController(BrokeragePresenter view) {
        this.view = view;
        initAlphaVantage();

        // Attach listeners
        view.getSearchButton().addActionListener(e -> searchStock());
    }

    private void initAlphaVantage() {
        Config cfg = Config.builder()
                .key("QD1VO3QMDYPFCTIB") // Replace with your actual key
                .timeOut(10)
                .build();
        AlphaVantage.api().init(cfg);
    }

    private void searchStock() {
        String stockSymbol = view.getStockNameField().getText().trim();

        if (stockSymbol.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol(stockSymbol)
                .interval(Interval.THIRTY_MIN)
                .outputSize(OutputSize.FULL)
                .dataType(DataType.JSON)
                .fetchSync();

        List<StockUnit> stockUnits = response.getStockUnits();

        if (stockUnits != null && !stockUnits.isEmpty()) {
            double latestPrice = stockUnits.get(0).getClose();
            view.getStockPriceLabel().setText(String.format("$%.2f", latestPrice));

            view.getBuyButton().setEnabled(true);
            view.getBuyButton().addActionListener(e -> buyAction(latestPrice, stockSymbol));
            view.getSellButton().setEnabled(true);
            view.getSellButton().addActionListener(e -> sellAction(latestPrice, stockSymbol));

            drawGraph(stockSymbol, stockUnits);
        } else {
            view.getStockPriceLabel().setText("N/A");
            JOptionPane.showMessageDialog(null, "No data found for this stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buyAction(double latestPrice, String stockSymbol) {
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

    private void sellAction(double latestPrice, String stockSymbol) {
        // (Reuse your existing sell logic here)
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

    private void drawGraph(String stockSymbol, List<StockUnit> stockUnits) {
        TimeSeries series = new TimeSeries(stockSymbol);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (StockUnit unit : stockUnits) {
            try {
                java.util.Date date = dateFormat.parse(unit.getDate());
                series.addOrUpdate(new Second(date), unit.getClose());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Stock Price Trends - " + stockSymbol,
                "Time",
                "Price ($)",
                dataset,
                false,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.decode("#222d64"));
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f));

        ChartPanel chartPanel = new ChartPanel(chart);
        JPanel graphPanel = view.getGraphPanel();
        graphPanel.removeAll();
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        graphPanel.validate();
    }

//    public static void main(String[] args) {
 //       BrokerageView view = new BrokerageView();
  //      new BrokerageController(view);
//        view.show();
//    }
}
