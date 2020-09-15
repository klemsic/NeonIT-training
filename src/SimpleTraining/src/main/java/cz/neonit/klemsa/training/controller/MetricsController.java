package cz.neonit.klemsa.training.controller;

import cz.neonit.klemsa.training.dao.communication.LogFileMessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.service.communication.CommunicationStatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MetricsController {

    @GetMapping("/metrics")
    public ResponseEntity<CommunicationStatistic> metrics(@RequestParam(value = "date", defaultValue = "today") String date) {
        Date dt = new Date();
        try {
            dt = new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            // Use today's date
        }

        CommunicationStatisticService service = new CommunicationStatisticService();
        CommunicationStatistic communicationStatistic = service.getCommunicationStatistic(dt, new LogFileMessageInfoLoader());

        return new ResponseEntity<>(communicationStatistic, HttpStatus.OK);
    }
}
