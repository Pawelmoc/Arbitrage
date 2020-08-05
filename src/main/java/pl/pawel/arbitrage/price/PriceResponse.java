package pl.pawel.arbitrage.price;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
class PriceResponse {

    @JsonProperty("price")
    private BigDecimal price;

}

