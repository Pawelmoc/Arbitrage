package pl.pawel.arbitrage.price;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class PriceServiceBinance {

    private final RestTemplate restTemplate;

    public BigDecimal getCurrentPrice(String symbol) {
        PriceResponse forObject = restTemplate.getForObject(
                "https://api.binance.com/api/v1/ticker/price?symbol=" + symbol + "TUSD",
                PriceResponse.class);

        return forObject.getPrice();
    }


}
