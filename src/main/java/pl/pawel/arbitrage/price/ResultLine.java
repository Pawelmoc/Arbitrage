package pl.pawel.arbitrage.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultLine {

    private Symbol symbol;
    private ExchangeDetails buyer;
    private ExchangeDetails seller;
    private Double profit;

    @Data
    @AllArgsConstructor
    public static class ExchangeDetails {

        private String exchange;
        private BigDecimal price;

    }


}
