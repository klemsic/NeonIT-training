package cz.neonit.klemsa.training.controller;

import cz.neonit.klemsa.training.dao.GitHubMessageInfoLoader;
import cz.neonit.klemsa.training.dao.LogFileMessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.service.KpiCounterService;
import cz.neonit.klemsa.training.service.CommunicationStatisticService;
import cz.neonit.klemsa.training.service.MetricsCallKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
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

    public MetricsController() {
    }

    @GetMapping("/metrics")
    public ResponseEntity<CommunicationStatistic> metrics(@RequestParam(value = "date", defaultValue = "today") String date) {

        CommunicationStatisticService service = new CommunicationStatisticService();
        CommunicationStatistic communicationStatistic = service.getCommunicationStatistic(date, new GitHubMessageInfoLoader());

        // Send via Kafka to KPIs.
        MetricsCallKafkaProducer mccp = new MetricsCallKafkaProducer();
        mccp.send(communicationStatistic);

        return new ResponseEntity<>(communicationStatistic, HttpStatus.OK);
    }
}
