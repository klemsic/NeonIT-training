package cz.neonit.klemsa.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    @GetMapping("/metrics")
    public void metrics(@RequestParam(value = "date", defaultValue = "") String date) {
        return;
    }
}
