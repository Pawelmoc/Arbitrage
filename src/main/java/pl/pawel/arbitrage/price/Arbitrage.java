package pl.pawel.arbitrage.price;


import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class Arbitrage {

    private String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private List<ResultLine> resultLines;

    public Arbitrage(List<ResultLine> resultLines) {
        this.resultLines = resultLines;

    }
}
