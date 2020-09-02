package pl.pawel.arbitrage.price.provider.coinbase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import pl.pawel.arbitrage.price.Symbol;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class CoinbaseClient {

    private final RestTemplate restTemplate;
    private final String coinbaseProUrl;

    public BigDecimal getCurrentPrice(Symbol symbol) {
        CoinbasePriceResponse forObject = getPriceFromCoinbase(symbol);
        log.info("Coinbase - symbol: " + symbol + ", price: " + forObject.getPrice());
        return forObject.getPrice();
    }

    private CoinbasePriceResponse getPriceFromCoinbase(Symbol symbol) {
        return restTemplate.getForObject(coinbaseProUrl + symbol + "-USD/ticker", CoinbasePriceResponse.class);
    }


}
