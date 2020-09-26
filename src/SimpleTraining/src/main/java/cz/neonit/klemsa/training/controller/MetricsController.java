package cz.neonit.klemsa.training.controller;

import cz.neonit.klemsa.training.dao.GitHubMessageInfoLoader;
import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.service.CommunicationStatisticService;
import cz.neonit.klemsa.training.kafka.MetricsCallKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    private final CommunicationStatisticService communicationStatisticService;
    private final MetricsCallKafkaProducer metricsCallKafkaProducer;

    /**
     *
     * @param communicationStatisticService
     * @param metricsCallKafkaProducer
     */
    @Autowired
    public MetricsController(CommunicationStatisticService communicationStatisticService,
                             MetricsCallKafkaProducer metricsCallKafkaProducer) {
        this.communicationStatisticService = communicationStatisticService;
        this.metricsCallKafkaProducer = metricsCallKafkaProducer;
    }

    /**
     *
     * @param date
     * @return
     */
    @GetMapping("/metrics")
    public ResponseEntity<CommunicationStatistic> metrics(@RequestParam(value = "date", defaultValue = "today") String date) {

        CommunicationStatistic communicationStatistic = communicationStatisticService.getCommunicationStatistic(date, new GitHubMessageInfoLoader());

        // Send via Kafka to KPIs.
        metricsCallKafkaProducer.send(communicationStatistic);

        return new ResponseEntity<>(communicationStatistic, HttpStatus.OK);
    }
}
