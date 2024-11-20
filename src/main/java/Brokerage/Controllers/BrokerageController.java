package Brokerage.Controllers;

import Brokerage.Entities.Stock;
import Brokerage.Presenters.BrokeragePresenter;
import Brokerage.UseCases.FetchStockDataUseCase;
import Brokerage.Views.BrokerageView;

import javax.swing.*;
//import java.awt.event.ActionListener;
import java.util.List;

public class BrokerageController {

    private final BrokerageView view;
    private final BrokeragePresenter presenter;

    public BrokerageController(BrokerageView view) {
        this.view = view;
        this.presenter = new BrokeragePresenter(new FetchStockDataUseCase());
        initializeListeners();
    }

    private void initializeListeners() {
        view.getSearchButton().addActionListener(e -> searchStock());
    }

    private void searchStock() {
        String stockSymbol = view.getStockNameField().getText().trim();
        if (stockSymbol.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Stock> stocks = presenter.fetchStockData(stockSymbol);
        if (!stocks.isEmpty()) {
            Stock latestStock = stocks.get(0);
            view.getStockPriceLabel().setText(String.format("$%.2f", latestStock.getPrice()));

            // Enable buy/sell actions (mock)
            view.getBuyButton().setEnabled(true);
            view.getSellButton().setEnabled(true);

            // Draw the graph (use extracted logic)
            drawGraph(stockSymbol, stocks);
        } else {
            JOptionPane.showMessageDialog(null, "No data found for this stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void drawGraph(String stockSymbol, List<Stock> stocks) {
        // (Reused graph drawing logic with time series, similar to before)
    }
}
