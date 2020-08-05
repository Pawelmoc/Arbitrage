package pl.pawel.arbitrage.price;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class PriceConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    PriceServiceBinance priceServiceBinance(RestTemplate restTemplate) {
        return new PriceServiceBinance(restTemplate);
    }

    @Bean
    PriceServiceCoinbasePro priceServiceCoinbasePro(RestTemplate restTemplate) {
        return new PriceServiceCoinbasePro(restTemplate);
    }
}
