package cz.neonit.klemsa.training.service;

import cz.neonit.klemsa.training.kafka.MetricsKafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class KpiService {
    private final MetricsKafkaListener metricsKafkaListener;
    private final KpiCounterService kpiCounterService;

    /**
     *
     * @param metricsKafkaListener
     * @param kpiCounterService
     */
    @Autowired
    public KpiService(MetricsKafkaListener metricsKafkaListener,
                      KpiCounterService kpiCounterService) {
        this.metricsKafkaListener = metricsKafkaListener;
        this.kpiCounterService = kpiCounterService;
    }

    //public Kpi getKpi() {}
}
