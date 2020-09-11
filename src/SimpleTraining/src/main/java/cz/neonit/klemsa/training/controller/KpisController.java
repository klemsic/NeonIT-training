package cz.neonit.klemsa.training.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KpisController {

    @GetMapping("/kpis")
    public ResponseEntity<String> kips(@RequestParam(value = "date", defaultValue = "") String date) {
        return null;
    }
}
