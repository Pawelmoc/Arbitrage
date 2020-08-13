package pl.pawel.arbitrage.price

import pl.pawel.arbitrage.price.provider.binance.BinanceClient
import pl.pawel.arbitrage.price.provider.coinbase.CoinbaseClient
import spock.lang.Specification
import pl.pawel.arbitrage.price.ResultLine.ExchangeDetails

class PriceServiceTest extends Specification {

    private BinanceClient binanceClient = Mock(BinanceClient);
    private CoinbaseClient coinbaseClient = Mock(CoinbaseClient);
    private PriceService priceService = new PriceService(binanceClient, coinbaseClient)

    def "Should return exchange with lower price"() {
        given:
        BigDecimal binancePrice = 54
        BigDecimal coinbaseProPrice = 56

        when:
        ExchangeDetails result = priceService.findBuyer(binancePrice, coinbaseProPrice)

        then:
        result.price == 54
        result.exchange == 'Binance'
    }

    def "Should return exchange with higher price"() {
        given:
        BigDecimal binancePrice = 54
        BigDecimal coinbaseProPrice = 56

        when:
        ExchangeDetails result = priceService.findSeller(binancePrice, coinbaseProPrice)

        then:
        result.price == 56
        result.exchange == 'Coinbase Pro'
    }

    def "Should calculate profit"() {
        given:
        binanceClient.getCurrentPrice(Symbol.BTC) >> new BigDecimal(10);
        coinbaseClient.getCurrentPrice(Symbol.BTC) >> new BigDecimal(11);
        binanceClient.getCurrentPrice(Symbol.ETH) >> new BigDecimal(12);
        coinbaseClient.getCurrentPrice(Symbol.ETH) >> new BigDecimal(13);
        binanceClient.getCurrentPrice(Symbol.XRP) >> new BigDecimal(14);
        coinbaseClient.getCurrentPrice(Symbol.XRP) >> new BigDecimal(15);
        binanceClient.getCurrentPrice(Symbol.BCH) >> new BigDecimal(16);
        coinbaseClient.getCurrentPrice(Symbol.BCH) >> new BigDecimal(17);
        binanceClient.getCurrentPrice(Symbol.LTC) >> new BigDecimal(18);
        coinbaseClient.getCurrentPrice(Symbol.LTC) >> new BigDecimal(19);
        binanceClient.getCurrentPrice(Symbol.LINK) >> new BigDecimal(20);
        coinbaseClient.getCurrentPrice(Symbol.LINK) >> new BigDecimal(21);

        when:
        Arbitage result = priceService.getArbitage();

        then:
        for (ResultLine value : result.resultLines) {
            assert value.profit == (value.seller.price.doubleValue() - value.buyer.price.doubleValue())/
                    value.buyer.price.doubleValue() * 100
        }
    }

}
