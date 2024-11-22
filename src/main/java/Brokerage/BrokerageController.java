package Brokerage;

import DataObjects.UserObject;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BrokerageController {
    UserObject loggedInUser;
    private final BrokeragePresenter brokeragePresenter;

    public BrokerageController(UserObject loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.brokeragePresenter = new BrokeragePresenter(this);
    }

    public void launch(){
        brokeragePresenter.showView();
    }

    public boolean onSearchStockTriggered(String stockSymbol){
        return !stockSymbol.isEmpty();
    }

    public boolean isStockFound(String stockSymbol){
        List<StockUnit> stocks = fetchStockData(stockSymbol);
        return !stocks.isEmpty();
    }

    public List<StockUnit> fetchStockData(String stockSymbol) {
        return FetchStockDataUseCase.execute(stockSymbol);
    }

    public List<StockUnit> retrieveStocks(String stockSymbol){
        return fetchStockData(stockSymbol);
    }

    public double onSearchStockSuccess(String stockSymbol) {
        List<StockUnit> stocks = fetchStockData(stockSymbol);
        StockUnit latestStock = stocks.get(0);
        return latestStock.getClose();
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
                JOptionPane.PLAIN_MESSAGE  // Message type
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
                JOptionPane.PLAIN_MESSAGE  // Message type
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

}
