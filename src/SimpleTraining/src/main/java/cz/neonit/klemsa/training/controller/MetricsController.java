package cz.neonit.klemsa.training.controller;

import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    @GetMapping("/metrics")
    public ResponseEntity<CommunicationStatistic> metrics(@RequestParam(value = "date", defaultValue = "") String date) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
