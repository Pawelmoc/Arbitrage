package pl.pawel.arbitrage.price.provider.coinbase

import org.springframework.web.client.RestTemplate
import pl.pawel.arbitrage.price.Symbol
import spock.lang.Specification

class CoinbaseClientTest extends Specification {

    static final String COINBASEPRO_URL = "coinbasepro/test"

    RestTemplate restTemplate = Mock(RestTemplate)
    CoinbaseClient coinbaseClient = new CoinbaseClient(restTemplate, COINBASEPRO_URL);

    def "Should return price of BTC from Coinbase Pro"() {
        given:
        Symbol symbol = Symbol.BTC

        and:
        restTemplate.getForObject(COINBASEPRO_URL + symbol + "-USD/ticker",
                CoinbasePriceResponse.class) >> new CoinbasePriceResponse(price: BigDecimal.valueOf(101));

        when:
        BigDecimal result = coinbaseClient.getCurrentPrice(symbol)

        then:
        result == 101
    }

}
