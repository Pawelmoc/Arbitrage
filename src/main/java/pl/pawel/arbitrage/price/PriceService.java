package pl.pawel.arbitrage.price;

import lombok.RequiredArgsConstructor;
import pl.pawel.arbitrage.price.ResultLine.ExchangeDetails;
import pl.pawel.arbitrage.price.provider.binance.BinanceClient;
import pl.pawel.arbitrage.price.provider.coinbase.CoinbaseClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PriceService {

    private final BinanceClient binanceClient;
    private final CoinbaseClient coinbaseClient;

    public Arbitrage getArbitrage() {
        List<ResultLine> resultList = new ArrayList<>();
        for (Symbol value : Symbol.values()) {
            BigDecimal binancePrice = binanceClient.getCurrentPrice(value);
            BigDecimal coinbaseProPrice = coinbaseClient.getCurrentPrice(value);
            ExchangeDetails buyer = findBuyer(binancePrice, coinbaseProPrice);
            ExchangeDetails seller = findSeller(binancePrice, coinbaseProPrice);
            Double profit = (seller.getPrice().doubleValue() - buyer.getPrice().doubleValue()) /
                    buyer.getPrice().doubleValue() * 100;
            resultList.add(new ResultLine(value, buyer, seller, profit));
        }
        return new Arbitrage(resultList);
    }

    public ExchangeDetails findBuyer(BigDecimal binancePrice, BigDecimal coinbaseProPrice) {
        if (binancePrice.compareTo(coinbaseProPrice) <= 0) {
            return new ExchangeDetails("Binance", binancePrice);
        }
        return new ExchangeDetails("Coinbase Pro", coinbaseProPrice);

    }

    public ExchangeDetails findSeller(BigDecimal binancePrice, BigDecimal coinbaseProPrice) {
        if (binancePrice.compareTo(coinbaseProPrice) > 0) {
            return new ExchangeDetails("Binance", binancePrice);
        }
        return new ExchangeDetails("Coinbase Pro", coinbaseProPrice);

    }
}
