package pl.pawel.arbitrage.price;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class PriceServiceCoinbasePro {

    private final RestTemplate restTemplate;

    public BigDecimal getCurrentPrice(String symbol) {
        PriceResponse forObject = restTemplate.getForObject(
                "https://api.pro.coinbase.com/products/" + symbol + "-USD/ticker",
                PriceResponse.class);

        return forObject.getPrice();
    }


}
