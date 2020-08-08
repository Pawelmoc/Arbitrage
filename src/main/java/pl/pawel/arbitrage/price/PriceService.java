package pl.pawel.arbitrage.price;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.pawel.arbitrage.price.ResultLine.ExchangeDetails;
import pl.pawel.arbitrage.price.provider.binance.BinanceClient;
import pl.pawel.arbitrage.price.provider.coinbase.CoinbaseClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PriceService {

    private final BinanceClient binanceClient;
    private final CoinbaseClient coinbaseClient;

    public Arbitage getArbitage() {
        List<ResultLine> resultList = new ArrayList<>();
        for (Symbol value : Symbol.values()) {
            ExchangeDetails buyer = findBuyer(value);
            ExchangeDetails seller = findSeller(value);
            Double profit = (seller.getPrice().doubleValue() - buyer.getPrice().doubleValue()) /
                    buyer.getPrice().doubleValue() * 100;
            resultList.add(new ResultLine(value, buyer, seller, profit));
        }
        return new Arbitage(resultList);
    }

    public ExchangeDetails findBuyer(Symbol symbol) {
        BigDecimal binancePrice = binanceClient.getCurrentPrice(symbol);
        BigDecimal coinbaseProPrice = coinbaseClient.getCurrentPrice(symbol);
        if (binancePrice.compareTo(coinbaseProPrice) <= 0) {
            return new ExchangeDetails("Binance", binancePrice);
        } else {
            return new ExchangeDetails("Coinbase Pro", coinbaseProPrice);
        }
    }

    public ExchangeDetails findSeller(Symbol symbol) {
        BigDecimal binancePrice = binanceClient.getCurrentPrice(symbol);
        BigDecimal coinbaseProPrice = coinbaseClient.getCurrentPrice(symbol);
        if (binancePrice.compareTo(coinbaseProPrice) > 0) {
            return new ExchangeDetails("Binance", binancePrice);
        } else {
            return new ExchangeDetails("Coinbase Pro", coinbaseProPrice);
        }
    }
}
