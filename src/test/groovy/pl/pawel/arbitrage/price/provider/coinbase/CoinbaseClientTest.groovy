package pl.pawel.arbitrage.price.provider.coinbase

import org.springframework.web.client.RestTemplate
import pl.pawel.arbitrage.price.Symbol
import spock.lang.Specification

class CoinbaseClientTest extends Specification {

    private static final String COINBASEPRO_URL = "coinbasepro/test"

    private RestTemplate restTemplate = Mock(RestTemplate)
    private CoinbaseClient coinbaseClient = new CoinbaseClient(restTemplate, COINBASEPRO_URL);

    def "Should return price of ETH from Coinbase Pro"() {
        given:
        Symbol symbol = Symbol.ETH

        and:
        restTemplate.getForObject(COINBASEPRO_URL + symbol + "-USD/ticker",
                PriceResponse.class) >> new PriceResponse(price: BigDecimal.valueOf(101));

        when:
        BigDecimal result = coinbaseClient.getCurrentPrice(symbol)

        then:
        result == 101
    }

}
