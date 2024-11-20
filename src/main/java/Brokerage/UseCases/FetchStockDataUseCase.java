package Brokerage.UseCases;

import Brokerage.Entities.Stock;
import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import java.util.ArrayList;
import java.util.List;

public class FetchStockDataUseCase {

    public List<Stock> execute(String stockSymbol) {
        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol(stockSymbol)
                .interval(Interval.THIRTY_MIN)
                .outputSize(OutputSize.FULL)
                .dataType(DataType.JSON)
                .fetchSync();

        List<Stock> stockData = new ArrayList<>();
        if (response.getStockUnits() != null) {
            for (StockUnit unit : response.getStockUnits()) {
                stockData.add(new Stock(unit.getDate(), unit.getClose()));
            }
        }
        return stockData;
    }
}
