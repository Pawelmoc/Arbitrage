package pl.pawel.arbitrage.price;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
class PriceController {

    private final PriceServiceBinance priceServiceBinance;
    private final PriceServiceCoinbasePro priceServiceCoinbasePro;

    @GetMapping("/binance/{symbol}")
    BigDecimal getPriceFromBinance(@PathVariable("symbol") String symbol) {

        return priceServiceBinance.getCurrentPrice(symbol);
    }

    @GetMapping("/coinbasepro/{symbol}")
    BigDecimal getPriceFromCoinbasePro(@PathVariable("symbol") String symbol) {

        return priceServiceCoinbasePro.getCurrentPrice(symbol);
    }

}
