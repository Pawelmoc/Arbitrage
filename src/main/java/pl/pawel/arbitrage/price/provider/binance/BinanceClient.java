package pl.pawel.arbitrage.price.provider.binance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import pl.pawel.arbitrage.price.Symbol;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class BinanceClient {

    private final RestTemplate restTemplate;
    private final String binanceUrl;

    public BigDecimal getCurrentPrice(Symbol symbol) {
        PriceResponse forObject = restTemplate.getForObject(
                binanceUrl + symbol + "TUSD",
                PriceResponse.class);
        return forObject.getPrice();
    }


}
