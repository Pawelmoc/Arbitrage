package pl.pawel.arbitrage.price;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
class PriceController {

    private final PriceService priceService;

    @GetMapping("/arbitage")
    Arbitage getResults() {
        return priceService.getArbitage();
    }

}
