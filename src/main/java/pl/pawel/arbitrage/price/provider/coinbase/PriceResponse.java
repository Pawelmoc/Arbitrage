package pl.pawel.arbitrage.price.provider.coinbase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceResponse {

    @JsonProperty("price")
    private BigDecimal price;

}

