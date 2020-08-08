package pl.pawel.arbitrage.price;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.pawel.arbitrage.price.provider.binance.BinanceClient;
import pl.pawel.arbitrage.price.provider.coinbase.CoinbaseClient;

@Configuration
class PriceConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    BinanceClient priceService(@Value("${binance.fetch-prices}") String binanceUrl,
                               RestTemplate restTemplate) {
        return new BinanceClient(restTemplate, binanceUrl);
    }

    @Bean
    CoinbaseClient priceServiceCoinbasePro(@Value("${coinbasePro.fetch-prices}") String coinbaseProUrl,
                                           RestTemplate restTemplate) {
        return new CoinbaseClient(restTemplate, coinbaseProUrl);
    }

    @Bean
    PriceService resultLineService(BinanceClient binanceClient, CoinbaseClient coinbaseClient) {
        return new PriceService(binanceClient, coinbaseClient);
    }


}
