package Exchange;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;

public class UseageExchange {
    public static double rate;

    public static void changeInto(String input, String output) {
        // OMY21EWV5Y9FEBUJ
        // G8PHFOEQL7JRT4WA
        Config cfg = Config.builder()
                .key("OMY21EWV5Y9FEBUJ")
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

        AlphaVantage.api()
                .exchangeRate()
                .fromCurrency(input)
                .toCurrency(output)
                .onSuccess((e) -> onData(e))
                .fetch();
    }

    public static void onData(ExchangeRateResponse response){
        CurrencyExchange.rate = response.getExchangeRate();
    }
}
