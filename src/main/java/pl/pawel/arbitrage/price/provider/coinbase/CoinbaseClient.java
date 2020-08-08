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
        PriceResponse forObject = restTemplate.getForObject(
                coinbaseProUrl + symbol + "-USD/ticker",
                PriceResponse.class);

        return forObject.getPrice();
    }


}
