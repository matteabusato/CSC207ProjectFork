package Brokerage.Presenters;

import Brokerage.Entities.Stock;
import Brokerage.UseCases.FetchStockDataUseCase;

import java.util.List;

public class BrokeragePresenter {

    private final FetchStockDataUseCase fetchStockDataUseCase;

    public BrokeragePresenter(FetchStockDataUseCase fetchStockDataUseCase) {
        this.fetchStockDataUseCase = fetchStockDataUseCase;
    }

    public List<Stock> fetchStockData(String stockSymbol) {
        return fetchStockDataUseCase.execute(stockSymbol);
    }
}
