package Exchange;

import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

import java.io.IOException;

public class APInew {
    public static void main(String[] args) throws IOException {
        FxQuote usdeur = YahooFinance.getFx(FxSymbols.USDEUR);
        FxQuote usdgbp = YahooFinance.getFx("USDGBP=X");
        System.out.println(usdeur);
        System.out.println(usdgbp);
    }
}
