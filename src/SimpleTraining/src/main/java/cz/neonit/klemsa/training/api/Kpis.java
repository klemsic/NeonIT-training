package cz.neonit.klemsa.training.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Kpis {

    @GetMapping("/kips")
    public void kips(@RequestParam(value = "date", defaultValue = "") String date) {
        return;
    }
}
