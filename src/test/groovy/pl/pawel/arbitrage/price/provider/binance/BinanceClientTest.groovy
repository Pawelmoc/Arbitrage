package pl.pawel.arbitrage.price.provider.binance

import org.springframework.web.client.RestTemplate
import pl.pawel.arbitrage.price.Symbol
import spock.lang.Specification

class BinanceClientTest extends Specification {

    static final String  BINANCE_URL = "binance/test"
    RestTemplate restTemplate = Mock(RestTemplate)
    BinanceClient binanceClient = new BinanceClient(restTemplate, BINANCE_URL);

    def "Should return price of ETH from Binance"() {
        given:
        Symbol symbol = Symbol.ETH

        and:
        restTemplate.getForObject(BINANCE_URL + symbol + "USDT",
                BinancePriceResponse.class) >> new BinancePriceResponse(price: BigDecimal.valueOf(101));

        when:
        BigDecimal result = binanceClient.getCurrentPrice(symbol)

        then:
        result == 101
    }
}
