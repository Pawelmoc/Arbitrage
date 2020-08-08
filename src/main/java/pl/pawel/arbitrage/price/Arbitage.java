package pl.pawel.arbitrage.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arbitage {

    private LocalDateTime dateTime = LocalDateTime.now();
    private List<ResultLine> resultLines;

    public Arbitage(List<ResultLine> resultLines) {
        this.resultLines = resultLines;
    }
}
