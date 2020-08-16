package pl.pawel.arbitrage.price;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ArbitrageController {

    private final PriceService priceService;

    @GetMapping("/arbitrage")
    public String showResults(Model model) {
        model.addAttribute("resultLines", priceService.getArbitrage().getResultLines());
        model.addAttribute("time", priceService.getArbitrage().getDateTime());
        return "result";
    }
}

