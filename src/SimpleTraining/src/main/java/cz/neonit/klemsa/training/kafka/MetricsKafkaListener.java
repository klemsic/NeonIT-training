package cz.neonit.klemsa.training.kafka;

import cz.neonit.klemsa.training.domain.communication.CommunicationStatistic;
import cz.neonit.klemsa.training.service.KpiCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Component that's provide Kafka listener for communication statistic.
 * @author tomasklemsa
 */
@Component
public class MetricsKafkaListener {
    private final CommunicationStatisticDeserializer communicationStatisticDeserializer;
    private final KpiCounterService kpiCounterService;

    /**
     *
     * @param communicationStatisticDeserializer
     */
    @Autowired
    public MetricsKafkaListener(CommunicationStatisticDeserializer communicationStatisticDeserializer,
                                KpiCounterService kpiCounterService) {
        this.communicationStatisticDeserializer = communicationStatisticDeserializer;
        this.kpiCounterService = kpiCounterService;
    }

    /**
     *
     * @param message
     */
    @KafkaListener(topics = "METRICS_CALL",groupId = "METRICS_CALL")
    public void processMessage(String message) {
        CommunicationStatistic communicationStatistic = communicationStatisticDeserializer.deserialize("",message.getBytes());
        kpiCounterService.addCommunicationStatistic(communicationStatistic);
    }
}
