package pl.pawel.arbitrage.price

import pl.pawel.arbitrage.price.provider.binance.BinanceClient
import pl.pawel.arbitrage.price.provider.coinbase.CoinbaseClient
import spock.lang.Specification
import pl.pawel.arbitrage.price.ResultLine.ExchangeDetails

class PriceServiceTest extends Specification {

    BinanceClient binanceClient = Mock(BinanceClient);
    CoinbaseClient coinbaseClient = Mock(CoinbaseClient);
    PriceService priceService = new PriceService(binanceClient, coinbaseClient)

    def "Should return exchange with lower price"() {
        given:
        BigDecimal binancePrice = 54
        BigDecimal coinbaseProPrice = 56

        when:
        ExchangeDetails buyer = priceService.findBuyer(binancePrice, coinbaseProPrice)

        then:
        buyer.price == 54
        buyer.exchange == 'Binance'
    }

    def "Should return exchange with higher price"() {
        given:
        BigDecimal binancePrice = 54
        BigDecimal coinbaseProPrice = 56

        when:
        ExchangeDetails seller = priceService.findSeller(binancePrice, coinbaseProPrice)

        then:
        seller.price == 56
        seller.exchange == 'Coinbase Pro'
    }

    def "Should calculate profit"() {
        given:

        binanceClient.getCurrentPrice(Symbol.BTC) >> new BigDecimal(10)
        coinbaseClient.getCurrentPrice(Symbol.BTC) >> new BigDecimal(11)

        when:
        Arbitrage result = priceService.getArbitrage();

        then:
        for (ResultLine value : result.resultLines) {
            assert value.profit == (value.seller.price.doubleValue() - value.buyer.price.doubleValue())/
                    value.buyer.price.doubleValue() * 100
        }
    }

}
